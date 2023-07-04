package com.example.javahealthrisks.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.services.CustomerService;

@Service
public class CustomerServiceImp implements CustomerService {

    @Override
    public CustomerModel create(CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public CustomerModel getOneById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneById'");
    }

    @Override
    public List<CustomerModel> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public void updateOneById(String id, CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateOneById'");
    }

    @Override
    public void removeOneById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOneById'");
    }

}
