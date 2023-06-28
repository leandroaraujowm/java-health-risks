package com.example.javahealthrisks.services;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.DiseaseRepository;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public DiseaseModel create(DiseaseDto diseaseDto) {
        var newDisease = new DiseaseModel();
        BeanUtils.copyProperties(diseaseDto, newDisease);

        return diseaseRepository.save(newDisease);
    }

    public Optional<DiseaseModel> getOneByName(String name) {
        return diseaseRepository.findById(name);
    }

}
