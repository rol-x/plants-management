package com.codeshop.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeshop.project.dto.CustomerDTO;
import com.codeshop.project.exception.EntityNotFoundException;
import com.codeshop.project.exception.IncompleteCustomerException;
import com.codeshop.project.model.Customer;
import com.codeshop.project.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer getCustomer(Long customerId) throws EntityNotFoundException {
        return customerRepository.findById(customerId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addCustomer(CustomerDTO customerDto) throws IncompleteCustomerException {
        if (customerDto.getFirstName() == null || customerDto.getLastName() == null || customerDto.getAddress() == null)
            throw new IncompleteCustomerException();
        return customerRepository.save(new Customer(customerDto));
    }

    @Override
    public Customer updateCustomer(Long customerId, CustomerDTO customerDto)
            throws EntityNotFoundException, IncompleteCustomerException {
        if (customerRepository.findById(customerId).isEmpty())
            throw new EntityNotFoundException();
        if (customerDto.getFirstName() == null || customerDto.getLastName() == null || customerDto.getAddress() == null)
            throw new IncompleteCustomerException();

        customerRepository.updateFirstName(customerId, customerDto.getFirstName());
        customerRepository.updateLastName(customerId, customerDto.getLastName());
        customerRepository.updateAddress(customerId, customerDto.getAddress());
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public Customer partiallyUpdateCustomer(Long customerId, CustomerDTO customerDto) throws EntityNotFoundException {
        if (customerRepository.findById(customerId).isEmpty())
            throw new EntityNotFoundException();

        if (customerDto.getFirstName() != null)
            customerRepository.updateFirstName(customerId, customerDto.getFirstName());
        if (customerDto.getLastName() != null)
            customerRepository.updateLastName(customerId, customerDto.getLastName());
        if (customerDto.getAddress() != null)
            customerRepository.updateAddress(customerId, customerDto.getAddress());
        return customerRepository.findById(customerId).orElse(null);
    }

    @Override
    public void deleteCustomer(Long customerId) throws EntityNotFoundException {
        if (customerRepository.findById(customerId).isEmpty())
            throw new EntityNotFoundException();

        customerRepository.deleteById(customerId);
    }

}
