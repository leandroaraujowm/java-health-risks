package com.example.javahealthrisks.dtos;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

public record CustomerDto(@NotBlank String name, @PastOrPresent LocalDate birthDay,
        @NotBlank @Length(max = 1) Character gender) {

}
