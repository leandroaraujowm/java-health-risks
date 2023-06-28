package com.example.javahealthrisks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public ResponseEntity<Object> getOneByName(@RequestParam(name = "name", required = true) String name) {
        Optional<DiseaseModel> disease = diseaseService.getOneByName(name);

        if (!disease.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(disease.get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<DiseaseModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(diseaseService.getAll());
    }

}
