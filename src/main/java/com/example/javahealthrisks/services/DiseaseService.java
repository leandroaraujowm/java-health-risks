package com.example.javahealthrisks.services;

import org.springframework.stereotype.Service;

import com.example.javahealthrisks.repositories.DiseaseRepository;

@Service
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    public DiseaseService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }
}
