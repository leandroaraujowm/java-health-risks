package com.example.javahealthrisks.controllers.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.javahealthrisks.services.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFoundExceptionHandler(NotFoundException e, HttpServletRequest request) {
        var standardException = new StandardException(e.getMessage(), "Not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardException);
    }

}
