package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // ✅ Create customer — only if email matches logged-in user
    
    
    @PostMapping
    public ResponseEntity<?> addCustomer(@RequestBody Customer customerInput) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // This gets email from JWT subject

        Optional<Customer> existing = customerService.getCustomerByEmail(email);
        if (existing.isPresent()) {
            return ResponseEntity.status(409).body("Customer already exists for this email.");
        }

        customerInput.setEmail(email); // Set email from login JWT

        Customer saved = customerService.saveCustomer(customerInput);
        return ResponseEntity.ok(saved);
    }



    // ✅ Get current logged-in customer info
    @GetMapping
    public ResponseEntity<?> getCustomerDetailsForLoggedInUser() {
        String email = getLoggedInEmail();

        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.status(404).body("Customer not found.");
        }
    }

    // ✅ Get by ID — only if ID's email matches logged-in user's email
    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable int id) {
        String email = getLoggedInEmail();

        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            if (customer.get().getEmail().equalsIgnoreCase(email)) {
                return ResponseEntity.ok(customer.get());
            } else {
                return ResponseEntity.status(403).body("Access denied to this customer.");
            }
        } else {
            return ResponseEntity.status(404).body("Customer not found.");
        }
    }

    // Helper method to get logged-in email from JWT
    private String getLoggedInEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName(); // email stored as subject in JWT
    }
}