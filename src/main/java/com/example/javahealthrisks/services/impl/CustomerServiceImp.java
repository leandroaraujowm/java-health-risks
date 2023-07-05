package com.example.javahealthrisks.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.repositories.CustomerRepository;
import com.example.javahealthrisks.services.CustomerService;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository repository;

    public CustomerServiceImp(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerModel create(CustomerDto customerDto) {
        Character gender = Character.toUpperCase(customerDto.gender());

        if (!gender.equals('M') && !gender.equals('F')) {
            throw new NotFoundException("Invalid gender");
        }
        
        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setGender(gender);
        
        return repository.save(customerModel);
    }

    @Override
    public CustomerModel getOneById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Invalid ID"));
    }

    @Override
    public List<CustomerModel> getAll() {
        return repository.findAll();
    }

    @Override
    public void updateOneById(Long id, CustomerDto customerDto) {
        CustomerModel customerModel = getOneById(id);
        BeanUtils.copyProperties(customerDto, customerModel);
        repository.save(customerModel);
    }

    @Override
    public void removeOneById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOneById'");
    }

}
