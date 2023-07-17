package com.example.javahealthrisks.services.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.CustomerRepository;
import com.example.javahealthrisks.services.CustomerService;
import com.example.javahealthrisks.services.DiseaseService;
import com.example.javahealthrisks.services.exceptions.BadRequestException;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository repository;
    private final DiseaseService diseaseService;

    public CustomerServiceImp(CustomerRepository repository, DiseaseService diseaseService) {
        this.repository = repository;
        this.diseaseService = diseaseService;
    }

    @Override
    public CustomerModel create(CustomerDto customerDto) {
        Character gender = Character.toUpperCase(customerDto.gender());

        if (!gender.equals('M') && !gender.equals('F')) {
            throw new BadRequestException("Invalid gender");
        }

        var customerModel = new CustomerModel();
        BeanUtils.copyProperties(customerDto, customerModel);
        customerModel.setGender(gender);

        return repository.save(customerModel);
    }

    @Override
    public CustomerModel getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Invalid customer ID"));
    }

    @Override
    public Page<CustomerModel> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void updateById(Long id, CustomerDto customerDto) {
        CustomerModel customerModel = getById(id);

        BeanUtils.copyProperties(customerDto, customerModel);
        repository.save(customerModel);
    }

    @Override
    public void removeById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void addDisease(Long customerId, String diseaseId) {
        DiseaseModel diseaseModel = diseaseService.getById(diseaseId);
        CustomerModel customerModel = getById(customerId);

        customerModel.getDiseases().add(diseaseModel);
        customerModel.setDiseaseScore();
        repository.save(customerModel);
    }

    @Override
    public void removeDisease(Long customerId, String diseaseId) {
        DiseaseModel diseaseModel = diseaseService.getById(diseaseId);
        CustomerModel customerModel = getById(customerId);

        customerModel.getDiseases().remove(diseaseModel);
        customerModel.setDiseaseScore();
        repository.save(customerModel);
    }

    @Override
    public List<CustomerModel> getRiskCustomerList() {
        return repository.findTop10ByOrderByDiseaseScoreDesc();
    }

}
