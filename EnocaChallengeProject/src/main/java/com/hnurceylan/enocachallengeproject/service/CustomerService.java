package com.hnurceylan.enocachallengeproject.service;

import com.hnurceylan.enocachallengeproject.entity.Customer;
import com.hnurceylan.enocachallengeproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    public ResponseEntity<?> addCustomer(Customer customer) {

        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            String message = "Name cannot be null or empty";
            return ResponseEntity.ok(message);

        }
        if (customer.getMail() == null || customer.getMail().trim().isEmpty()) {
            String message = "Email cannot be null or empty";
            return ResponseEntity.ok(message);

        }
        if (customerRepository.existsByMail(customer.getMail())) {
           String message = "Email already exists";
           return ResponseEntity.ok(message);
        }

        customerRepository.save(customer);
        return ResponseEntity.ok("customer successfully registered");

    }
}
