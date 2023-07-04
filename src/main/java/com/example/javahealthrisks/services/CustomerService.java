package com.example.javahealthrisks.services;

import java.util.List;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;

public interface CustomerService {

    CustomerModel create(CustomerDto customerDto);

    CustomerModel getOneById(String id);

    List<CustomerModel> getAll();

    void updateOneById(String id, CustomerDto customerDto);

    void removeOneById(String id);

}
