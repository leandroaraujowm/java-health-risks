package com.example.javahealthrisks.controllers;

import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.javahealthrisks.dtos.DiseaseDto;
import com.example.javahealthrisks.dtos.UpdateDiseaseDto;
import com.example.javahealthrisks.models.DiseaseModel;
import com.example.javahealthrisks.repositories.DiseaseRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class DiseaseControllerIT {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    DiseaseRepository diseaseRepository;

    List<DiseaseModel> diseaseList = List.of(
            new DiseaseModel("A", 1),
            new DiseaseModel("B", 2),
            new DiseaseModel("C", 1),
            new DiseaseModel("D", 2),
            new DiseaseModel("E", 1),
            new DiseaseModel("F", 2),
            new DiseaseModel("G", 1),
            new DiseaseModel("H", 2),
            new DiseaseModel("I", 1),
            new DiseaseModel("J", 2));

    @BeforeEach
    void initEach() {
        diseaseRepository.deleteAll();
        diseaseRepository.saveAll(diseaseList);
    }

    @Test
    void create_should_thrown_exception() throws Exception {
        var diseaseDto = new DiseaseDto("Flu", 0);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/disease")
                .content(objectMapper.writeValueAsString(diseaseDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void create_should_return_location_id() throws Exception {
        var diseaseDto = new DiseaseDto("Flu", 1);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/disease")
                .content(objectMapper.writeValueAsString(diseaseDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "http://localhost/disease/Flu"));
    }

    @Test
    void getById_should_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/disease/Z"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void getById_should_return_disease() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/disease/A"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(diseaseList.get(0))));
    }

    @Test
    void getAll_should_return_disease_list() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/disease"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(diseaseList.size())));
    }

    @Test
    void updateById_should_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/disease/Z")
                .content(objectMapper.writeValueAsString(new UpdateDiseaseDto(1)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void updateById_should_not_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/disease/A")
                .content(objectMapper.writeValueAsString(new UpdateDiseaseDto(2)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void removeById_should_not_thrown_exception() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/disease/Z"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
