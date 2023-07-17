package com.example.javahealthrisks.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;

public interface CustomerService {

    CustomerModel create(CustomerDto customerDto);

    CustomerModel getById(Long id);

    Page<CustomerModel> getAll(Pageable pageable);

    void updateById(Long id, CustomerDto customerDto);

    void removeById(Long id);

    void addDisease(Long customerId, String diseaseId);

    void removeDisease(Long customerId, String diseaseId);

    List<CustomerModel> getRiskCustomerList();

}
