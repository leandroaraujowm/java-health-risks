package com.example.javahealthrisks.services;

import java.util.List;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;

public interface DiseaseService {

    DiseaseModel create(DiseaseDto diseaseDto);

    DiseaseModel getOneById(String id);

    List<DiseaseModel> getAll();

    void updateOneById(String id, UpdateDiseaseDto updateDiseaseDto);

    void removeOneById(String id);

}
