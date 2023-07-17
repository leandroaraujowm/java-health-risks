package com.example.javahealthrisks.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;

public interface DiseaseService {

    DiseaseModel create(DiseaseDto diseaseDto);

    DiseaseModel getById(String id);

    Page<DiseaseModel> getAll(Pageable pageable);

    void updateById(String id, UpdateDiseaseDto updateDiseaseDto);

    void removeById(String id);

}
