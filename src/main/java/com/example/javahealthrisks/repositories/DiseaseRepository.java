package com.example.javahealthrisks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.javahealthrisks.models.DiseaseModel;

public interface DiseaseRepository extends JpaRepository<DiseaseModel, String> {

}
