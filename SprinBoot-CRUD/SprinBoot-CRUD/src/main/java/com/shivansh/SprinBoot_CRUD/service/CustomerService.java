package com.shivansh.SprinBoot_CRUD.service;

import com.shivansh.SprinBoot_CRUD.entity.Customer;
import com.shivansh.SprinBoot_CRUD.exception.DuplicateRecordException;
import com.shivansh.SprinBoot_CRUD.exception.ResourceNotFoundException;
import com.shivansh.SprinBoot_CRUD.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {

    // constructor injection
    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // CRUD operations
    public Customer createCustomer(Customer customer) {
        Customer customer1 = customerRepository.save(customer);
        if(isEmailExist(customer1)){
            throw new DuplicateRecordException("User with email already exist , try new email..");
        }
        return customer1;

    }
    private boolean isEmailExist(Customer customer1) {
        return customerRepository.existsByEmail(customer1.getEmail());
    }

    // updated method .....
    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findByIdAndIsDeletedIsFalse(id);
        return customer.orElseThrow(
                ()-> new ResourceNotFoundException("Record with id "+id+" not found"));
    }

    // updated method .....
    public List<Customer> getAllCustomers() {
        return customerRepository.findAllByIsDeletedFalse();
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    public Boolean softDeleteCustomerById(Long id) {
        Optional<Customer> customerOptional = customerRepository.findByIdAndIsDeletedIsFalse(id);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            customer.setDeleted(true);
            customerRepository.save(customer);
            return true;
        }
        return false;
    }

    public void customerById(Long id) {
        customerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Record with id "+id+" not found"));
    }
    public List<Customer> getAllCustomersByPlace(String place) {
        // Implementation for fetching customers by place
        List<Customer> customers = customerRepository.findAllByIsDeletedFalse();
        // You can filter the customers based on place here if needed
        List<Customer> customersByPlace = customers
                .stream()
                .filter(customer -> customer.getAddress().equals(place))
                .toList();
        return customersByPlace;
    }
}


