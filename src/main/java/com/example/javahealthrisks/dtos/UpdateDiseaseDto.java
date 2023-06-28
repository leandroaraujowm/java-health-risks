package com.example.javahealthrisks.dtos;

import jakarta.validation.constraints.NotNull;

public record UpdateDiseaseDto(@NotNull Integer grade) {

}
