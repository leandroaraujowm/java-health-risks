package com.example.javahealthrisks.services;

import java.util.List;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;

public interface CustomerService {

    CustomerModel create(CustomerDto customerDto);

    CustomerModel getOneById(Long id);

    List<CustomerModel> getAll();

    void updateOneById(Long id, CustomerDto customerDto);

    void removeOneById(Long id);

    void addDisease(Long customerId, String diseaseId);

    void removeDisease(Long customerId, String diseaseId);

    void getRiskCustomerList();

}
