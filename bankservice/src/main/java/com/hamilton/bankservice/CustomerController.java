package com.hamilton.bankservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@CrossOrigin
@RestController
public class CustomerController {
    @Autowired
    private CustomerDAO customers;
    @Autowired
    private AccountDAO account;

    @DeleteMapping(value = "/customer/{id}")
    public double deleteCustomer(@PathVariable Integer id) {
        return customers.deleteCustomer(id);
    }

    @GetMapping(value = "/customer/{id}")
    public Customer getCustomer(@PathVariable Integer id) {
        return customers.getById(id);
    }

    @PostMapping(value = "/customer")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer newCustomer(@RequestBody String customer) {
        return customers.registerCustomer(customer);
    }
}

