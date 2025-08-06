package com.example.demo.service;

import com.example.demo.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(int id);
    Optional<Customer> getCustomerByEmail(String email);
}
