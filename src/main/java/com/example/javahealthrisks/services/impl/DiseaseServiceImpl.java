package com.example.javahealthrisks.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.DiseaseRepository;
import com.example.javahealthrisks.services.DiseaseService;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@Service
public class DiseaseServiceImpl implements DiseaseService {

    private final DiseaseRepository repository;

    public DiseaseServiceImpl(DiseaseRepository repository) {
        this.repository = repository;
    }

    @Override
    public DiseaseModel create(DiseaseDto diseaseDto) {
        var newDisease = new DiseaseModel();
        BeanUtils.copyProperties(diseaseDto, newDisease);

        return repository.save(newDisease);
    }

    @Override
    public DiseaseModel getById(String id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Invalid ID"));
    }

    @Override
    public List<DiseaseModel> getAll() {
        return repository.findAll();
    }

    @Override
    public void updateById(String id, UpdateDiseaseDto updateDiseaseDto) {
        DiseaseModel diseaseModel = repository.findById(id).get();
        diseaseModel.setGrade(updateDiseaseDto.grade());
        repository.save(diseaseModel);
    }

    @Override
    public void removeById(String id) {
        repository.deleteById(id);
    }

}
