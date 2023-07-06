package com.example.javahealthrisks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.javahealthrisks.models.RiskCustomerModel;

public interface RiskCustomerRepository extends JpaRepository<RiskCustomerModel, Long> {

}
