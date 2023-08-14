package com.example.javahealthrisks.controllers;

import java.time.LocalDate;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.CustomerRepository;
import com.example.javahealthrisks.repositories.DiseaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    DiseaseRepository diseaseRepository;

    List<CustomerModel> customerList = List.of(
            new CustomerModel(1L, "Arthur Morgan", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(2L, "John Marston", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(3L, "Dutch", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(4L, "Hosea Matthews", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(5L, "Micah Bell", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(6L, "Lenny Summers", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(7L, "Bill Williamson", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(8L, "Pearson", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(9L, "Sean Macguire", LocalDate.now(), 'M', 0.0, null, null, null),
            new CustomerModel(10L, "Charles Smith", LocalDate.now(), 'M', 0.0, null, null, null));

    List<DiseaseModel> diseaseList = List.of(
            new DiseaseModel("A", 1),
            new DiseaseModel("B", 2),
            new DiseaseModel("C", 1),
            new DiseaseModel("D", 2));

    @BeforeAll
    void initEach() {
        customerRepository.deleteAll();
        customerRepository.saveAll(customerList);
        diseaseRepository.deleteAll();
        diseaseRepository.saveAll(diseaseList);
    }

    @Test
    void create_should_thrown_exception_for_gender() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .content(objectMapper.writeValueAsString(new CustomerDto("Jhon Doe", LocalDate.of(2000, 12, 25), 'X')))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void create_should_thrown_exception_for_birthDay() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .content(objectMapper.writeValueAsString(new CustomerDto("Jhon Doe", LocalDate.now().plusDays(1), 'M')))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void create_should_return_resource_location_id() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer")
                .content(objectMapper.writeValueAsString(new CustomerDto("Jhon Doe", LocalDate.of(2000, 12, 25), 'M')))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    void getById_should_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer/99"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getById_should_return_customer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.gender", Matchers.is("M")));
    }

    @Test
    void getAll_should_return_customer_list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(customerList.size())));
    }

    @Test
    void updateById_should_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/customer/99")
                .content(objectMapper.writeValueAsString(new CustomerDto("Jhon Doe", LocalDate.now(), 'M')))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateById_should_not_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .put("/customer/1")
                .content(objectMapper.writeValueAsString(new CustomerDto("Arthur Morgan ATT", LocalDate.now(), 'M')))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void removeById_should_not_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/99"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void addDisease_should_thrown_disease_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer/1/disease/Z"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addDisease_should_thrown_customer_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer/99/disease/A"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void addDisease_should_not_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/customer/1/disease/A"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void removeDisease_should_thrown_disease_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/1/disease/Z"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void removeDisease_should_thrown_customer_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/99/disease/A"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void removeDisease_should_not_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customer/1/disease/A"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getRiskCustomerList_should_return_risk_customer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customer/disease"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)));
    }

}
