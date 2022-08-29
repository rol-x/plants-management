package com.codeshop.plants.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeshop.plants.dto.CustomerDTO;
import com.codeshop.plants.exception.EntityNotFoundException;
import com.codeshop.plants.exception.IncompleteCustomerException;
import com.codeshop.plants.model.Customer;

@Service
public interface CustomerService {

    public Customer getCustomer(Long customerId) throws EntityNotFoundException;

    public List<Customer> getAllCustomers();

    public Customer addCustomer(CustomerDTO customerDto) throws IncompleteCustomerException;

    public Customer updateCustomer(Long id, CustomerDTO customerDto)
            throws EntityNotFoundException, IncompleteCustomerException;

    public Customer partiallyUpdateCustomer(Long id, CustomerDTO customerDto) throws EntityNotFoundException;

    public void deleteCustomer(Long customerId) throws EntityNotFoundException;
}
