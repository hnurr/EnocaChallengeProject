package com.hnurceylan.enocachallengeproject.controller;


import com.hnurceylan.enocachallengeproject.entity.Customer;
import com.hnurceylan.enocachallengeproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer){

        return customerService.addCustomer(customer);

    }
}
