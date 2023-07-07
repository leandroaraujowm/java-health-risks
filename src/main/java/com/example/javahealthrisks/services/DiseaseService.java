package com.example.javahealthrisks.services;

import java.util.List;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;

public interface DiseaseService {

    DiseaseModel create(DiseaseDto diseaseDto);

    DiseaseModel getById(String id);

    List<DiseaseModel> getAll();

    void updateById(String id, UpdateDiseaseDto updateDiseaseDto);

    void removeById(String id);

}
