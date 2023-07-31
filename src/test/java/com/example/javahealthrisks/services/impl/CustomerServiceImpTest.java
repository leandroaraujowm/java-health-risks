package com.example.javahealthrisks.services.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.CustomerRepository;
import com.example.javahealthrisks.services.DiseaseService;
import com.example.javahealthrisks.services.exceptions.BadRequestException;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImpTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private DiseaseService diseaseService;
    @InjectMocks
    private CustomerServiceImp customerService;

    @Test
    void create_should_thrown_exception() {
        var newCustomerDto = new CustomerDto("John Doe", LocalDate.of(2000, 12, 25), 'x');

        assertThrows(BadRequestException.class, () -> customerService.create(newCustomerDto));
        verify(customerRepository, times(0)).save(any(CustomerModel.class));
    }

    @Test
    void create_should_return_new_customer() {
        var customerModel = new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                Instant.now(), new HashSet<DiseaseModel>());
        var customerDto = new CustomerDto("John Doe", LocalDate.of(2000, 12, 25), 'M');

        when(customerRepository.save(any(CustomerModel.class))).thenReturn(customerModel);
        CustomerModel newCustomer = customerService.create(customerDto);

        assertNotNull(newCustomer);
        assertEquals(1L, newCustomer.getId());
    }

    @Test
    void getById_should_thrown_exception() {
        when(customerRepository.findById(anyLong())).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, () -> customerService.getById(1L), "Invalid customer ID");
    }

    @Test
    void getById_should_return_customer() {
        var newCustomer = new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                Instant.now(), new HashSet<DiseaseModel>());

        when(customerRepository.findById(1L)).thenReturn(Optional.of(newCustomer));
        CustomerModel savedCustomer = customerService.getById(1L);

        assertEquals(newCustomer, savedCustomer);
    }

    @Test
    void getAll_should_return_page() {
        List<CustomerModel> customerList = List
                .of(new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                        Instant.now(), new HashSet<DiseaseModel>()));

        when(customerRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(customerList));
        Page<CustomerModel> customerPage = customerService.getAll(Pageable.unpaged());

        assertNotNull(customerPage);
        assertEquals(1L, customerPage.getContent().get(0).getId());
    }

    @Test
    void updateById_should_thrown_exception() {
        var updatedCustomerDto = new CustomerDto("John Doe ATT", LocalDate.of(2000, 12, 25), 'M');

        assertThrows(NotFoundException.class, () -> customerService.updateById(1L, updatedCustomerDto));
        verify(customerRepository, times(0)).save(any(CustomerModel.class));
    }

    @Test
    void updateById_should_update_customer() {
        var savedCustomer = new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                Instant.now(), new HashSet<DiseaseModel>());
        var updateCustomerDto = new CustomerDto("John Doe ATT", LocalDate.of(2000, 12, 25), 'M');

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(savedCustomer));
        when(customerRepository.save(any(CustomerModel.class))).thenReturn(null);

        assertDoesNotThrow(() -> customerService.updateById(1L, updateCustomerDto));
        verify(customerRepository, times(1)).save(any(CustomerModel.class));
    }

    @Test
    void removeById_should_not_throw_exception() {
        doNothing().when(customerRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> customerService.removeById(1L));
        verify(customerRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void addDisease_should_not_throw_exception() {
        var savedDisease = new DiseaseModel("Flu", 1);
        var savedCustomer = new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                Instant.now(), new HashSet<DiseaseModel>());

        when(diseaseService.getById(anyString())).thenReturn(savedDisease);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(savedCustomer));
        when(customerRepository.save(any(CustomerModel.class))).thenReturn(null);

        assertDoesNotThrow(() -> customerService.addDisease(1L, "Flu"));
        verify(customerRepository, times(1)).save(savedCustomer);
    }

    @Test
    void removeDisease_should_not_throw_exception() {
        var savedDisease = new DiseaseModel("Flu", 1);
        var savedCustomer = new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                Instant.now(), new HashSet<DiseaseModel>());

        when(diseaseService.getById(anyString())).thenReturn(savedDisease);
        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(savedCustomer));
        when(customerRepository.save(any(CustomerModel.class))).thenReturn(null);

        assertDoesNotThrow(() -> customerService.removeDisease(1L, "Flu"));
        verify(customerRepository, times(1)).save(savedCustomer);
    }

    @Test
    void getRiskCustomerList_should_return_list() {
        var customerModel = new CustomerModel(1L, "John Doe", LocalDate.of(2000, 12, 25), 'M', 0.0, Instant.now(),
                Instant.now(), new HashSet<DiseaseModel>());
        var diseaseModel = new DiseaseModel("Flu", 1);
        customerModel.getDiseases().add(diseaseModel);
        customerModel.setDiseaseScore();
        List<CustomerModel> customerList = List.of(customerModel);

        when(customerRepository.findTop10ByOrderByDiseaseScoreDesc()).thenReturn(customerList);
        List<CustomerModel> savedRiskCustomer = customerService.getRiskCustomerList();

        assertNotNull(savedRiskCustomer);
        assertDoesNotThrow(() -> customerService.getRiskCustomerList());
        assertEquals(customerModel, savedRiskCustomer.get(0));
    }

}