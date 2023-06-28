package com.example.javahealthrisks.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DiseaseDto(@NotBlank String name, @NotNull Integer grade) {

}
