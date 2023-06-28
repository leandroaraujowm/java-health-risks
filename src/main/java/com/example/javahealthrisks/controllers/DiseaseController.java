package com.example.javahealthrisks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.services.DiseaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    private final DiseaseService service;

    public DiseaseController(DiseaseService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DiseaseModel> create(@RequestBody @Valid DiseaseDto requestBody) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(requestBody));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneById(@PathVariable String id) {
        Optional<DiseaseModel> diseaseOpt = service.getOneById(id);

        if (!diseaseOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(diseaseOpt.get());
    }

    @GetMapping
    public ResponseEntity<List<DiseaseModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateOneById(@PathVariable String id, @RequestBody UpdateDiseaseDto requestBody) {
        service.updateOneById(id, requestBody);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeOneById(@PathVariable String id) {
        service.removeOneById(id);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
