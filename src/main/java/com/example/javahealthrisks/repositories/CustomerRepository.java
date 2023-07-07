package com.example.javahealthrisks.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.javahealthrisks.models.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    List<CustomerModel> findTop10ByOrderByDiseaseScoreDesc();

}
