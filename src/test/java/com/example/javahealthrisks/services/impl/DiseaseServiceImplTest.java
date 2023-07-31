package com.example.javahealthrisks.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.DiseaseRepository;
import com.example.javahealthrisks.services.exceptions.BadRequestException;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class DiseaseServiceImplTest {

    @Mock
    private DiseaseRepository diseaseRepository;
    @InjectMocks
    private DiseaseServiceImpl diseaseService;

    @Test
    void create_should_thrown_exception() {
        assertThrows(BadRequestException.class, () -> diseaseService.create(new DiseaseDto("Flu", 0)));
        assertThrows(BadRequestException.class, () -> diseaseService.create(new DiseaseDto("Flu", 3)));
    }

    @Test
    void create_should_return_new_disease() {
        var diseaseDto = new DiseaseDto("Flu", 1);
        var diseaseModel = new DiseaseModel("Flu", 1);

        when(diseaseRepository.save(any(DiseaseModel.class))).thenReturn(diseaseModel);
        DiseaseModel savedDisease = diseaseService.create(diseaseDto);

        assertNotNull(savedDisease);
        assertEquals(diseaseModel, savedDisease);
    }

    @Test
    void getById_should_thrown_exception() {
        assertThrows(NotFoundException.class, () -> diseaseService.getById("Flu"), "Invalid disease ID");
    }

    @Test
    void getById_should_return_disease() {
        var diseaseModel = new DiseaseModel("Flu", 1);

        when(diseaseRepository.findById(anyString())).thenReturn(Optional.of(diseaseModel));
        DiseaseModel savedDisease = diseaseService.getById("Flu");

        assertNotNull(savedDisease);
        assertEquals(diseaseModel, savedDisease);
    }

    @Test
    void getAll_should_return_page() {
        Page<DiseaseModel> diseasePage = new PageImpl<>(List.of(new DiseaseModel("Flu", 1)));

        when(diseaseRepository.findAll(Pageable.unpaged())).thenReturn(diseasePage);
        Page<DiseaseModel> savedDiseasePage = diseaseService.getAll(Pageable.unpaged());

        assertNotNull(savedDiseasePage);
        assertEquals(diseasePage, savedDiseasePage);
    }

    @Test
    void updateById_should_thrown_exception() {
        assertThrows(NotFoundException.class, () -> diseaseService.updateById("Flu", null));
        verify(diseaseRepository, times(0)).save(any(DiseaseModel.class));
    }

    @Test
    void updateById_should_update_disease() {
        var diseaseModel = new DiseaseModel("Flu", 1);
        var updateDiseaseDto = new UpdateDiseaseDto(2);

        when(diseaseRepository.findById(anyString())).thenReturn(Optional.of(diseaseModel));

        assertDoesNotThrow(() -> diseaseService.updateById("Flu", updateDiseaseDto));
        verify(diseaseRepository, times(1)).save(any(DiseaseModel.class));
    }

    @Test
    void removeById_should_not_thrown_exception() {
        assertDoesNotThrow(() -> diseaseService.removeById("Flu"));
        verify(diseaseRepository, times(1)).deleteById(anyString());
    }

}
