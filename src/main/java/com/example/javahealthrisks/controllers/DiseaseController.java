package com.example.javahealthrisks.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.services.DiseaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    private final DiseaseService diseaseService;

    public DiseaseController(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }

    @PostMapping
    public ResponseEntity<DiseaseModel> create(@RequestBody @Valid DiseaseDto requestBody) {
        return ResponseEntity.status(HttpStatus.CREATED).body(diseaseService.create(requestBody));
    }

}
