package com.example.javahealthrisks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.javahealthrisks.models.CustomerModel;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

}
