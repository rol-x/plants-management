package com.codeshop.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeshop.project.dto.CustomerDTO;
import com.codeshop.project.exception.EntityNotFoundException;
import com.codeshop.project.exception.IncompleteCustomerException;
import com.codeshop.project.model.Customer;
import com.codeshop.project.service.CustomerService;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable("id") Long customerId) throws EntityNotFoundException {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("")
    public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDto)
            throws IncompleteCustomerException {
        Customer savedCustomer = customerService.addCustomer(customerDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Content-Location", "/users/" + savedCustomer.getId().toString()).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDTO customerDto)
            throws EntityNotFoundException, IncompleteCustomerException {
        return customerService.updateCustomer(customerId, customerDto);
    }

    @PatchMapping("/{id}")
    public Customer partiallyUpdateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomerDTO customerDto)
            throws EntityNotFoundException {
        return customerService.partiallyUpdateCustomer(customerId, customerDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") Long customerId)
            throws EntityNotFoundException {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
