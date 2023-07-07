package com.example.javahealthrisks.services;

import java.util.List;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;

public interface CustomerService {

    CustomerModel create(CustomerDto customerDto);

    CustomerModel getById(Long id);

    List<CustomerModel> getAll();

    void updateById(Long id, CustomerDto customerDto);

    void removeById(Long id);

    void addDisease(Long customerId, String diseaseId);

    void removeDisease(Long customerId, String diseaseId);

    List<CustomerModel> getRiskCustomerList();

}
