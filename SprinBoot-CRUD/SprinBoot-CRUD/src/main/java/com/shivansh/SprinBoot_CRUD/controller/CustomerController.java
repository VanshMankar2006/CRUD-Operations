package com.shivansh.SprinBoot_CRUD.controller;

import com.shivansh.SprinBoot_CRUD.entity.Customer;
import com.shivansh.SprinBoot_CRUD.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    // dependency injection
    private CustomerService customerService;
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // create customer
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer cus = customerService.createCustomer(customer);
        // return status code in response Body with customer object
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(cus);
    }

    // read one customer
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
//        if (customer == null) {
//            return ResponseEntity.notFound().build();
//        }  Not required due to addition of global exception handler
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    // read all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    // update customer
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer existingCustomer = customerService.getCustomerById(id);
        customer.setId(id);
        Customer updatedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.ok(updatedCustomer);
    }


    // delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomerById(@PathVariable Long id) {
        customerService.customerById(id);
        customerService.deleteCustomerById(id);
        return ResponseEntity.ok("Customer with id " + id + " deleted successfully");
    }

    // soft delete customer
    @PatchMapping("/{id}")
    public ResponseEntity<String> softDeleteCustomerById(@PathVariable Long id) {
        Customer existingCustomer = customerService.getCustomerById(id);

        Boolean isDeleted = customerService.softDeleteCustomerById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Customer with id " + id + " soft deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to soft delete customer with id " + id);
        }
    }

    // method to get customers from place
    @GetMapping("/place/{place}")
    public ResponseEntity<List<Customer>> getCustomersByPlace(@PathVariable String place) {
        List<Customer> customers = customerService.getAllCustomersByPlace(place);
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }


}