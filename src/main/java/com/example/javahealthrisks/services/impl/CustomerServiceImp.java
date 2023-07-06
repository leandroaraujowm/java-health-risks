package com.example.javahealthrisks.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.CustomerRepository;
import com.example.javahealthrisks.repositories.RiskCustomerRepository;
import com.example.javahealthrisks.services.CustomerService;
import com.example.javahealthrisks.services.DiseaseService;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository repository;
    private final RiskCustomerRepository riskCustomerRepository;
    private final DiseaseService diseaseService;

    public CustomerServiceImp(CustomerRepository repository, RiskCustomerRepository riskCustomerRepository,
            DiseaseService diseaseService) {
        this.repository = repository;
        this.riskCustomerRepository = riskCustomerRepository;
        this.diseaseService = diseaseService;
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
        repository.deleteById(id);
    }

    @Override
    public void addDisease(Long customerId, String diseaseId) {
        DiseaseModel diseaseModel = diseaseService.getOneById(diseaseId);
        CustomerModel customerModel = getOneById(customerId);

        customerModel.getDiseases().add(diseaseModel);
        repository.save(customerModel);
    }

    @Override
    public void removeDisease(Long customerId, String diseaseId) {
        DiseaseModel diseaseModel = diseaseService.getOneById(diseaseId);
        CustomerModel customerModel = getOneById(customerId);

        customerModel.getDiseases().remove(diseaseModel);
        repository.save(customerModel);
    }

    @Override
    public Object getRiskCustomerList() {
        return riskCustomerRepository.findAll();
    }

}
