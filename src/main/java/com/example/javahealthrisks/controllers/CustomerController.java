package com.example.javahealthrisks.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.javahealthrisks.dtos.CustomerDto;
import com.example.javahealthrisks.models.CustomerModel;
import com.example.javahealthrisks.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CustomerDto requestBody) {
        var customer = service.create(requestBody);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerModel> getOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getOneById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerModel>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOneById(@PathVariable Long id, @RequestBody @Valid CustomerDto requestBody) {
        service.updateOneById(id, requestBody);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeOneById(@PathVariable Long id) {
        service.removeOneById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{customerId}/disease/{diseaseId}")
    public ResponseEntity<Void> addDisease(@PathVariable Long customerId, @PathVariable String diseaseId) {
        service.addDisease(customerId, diseaseId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
