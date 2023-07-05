package com.example.javahealthrisks.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record CustomerDto(@NotBlank String name, @PastOrPresent LocalDate birthDay,
                @NotNull Character gender) {

}
