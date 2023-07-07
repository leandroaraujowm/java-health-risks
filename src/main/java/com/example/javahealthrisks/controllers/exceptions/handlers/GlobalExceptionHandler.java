package com.example.javahealthrisks.controllers.exceptions.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.javahealthrisks.services.exceptions.BadRequestException;
import com.example.javahealthrisks.services.exceptions.NotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardException> notFoundExceptionHandler(NotFoundException e, HttpServletRequest request) {
        var standardException = new StandardException("Not found", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardException);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<StandardException> badRequestExceptionHandler(BadRequestException e,
            HttpServletRequest request) {
        var standardException = new StandardException("Bad request", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardException);
    }

}
