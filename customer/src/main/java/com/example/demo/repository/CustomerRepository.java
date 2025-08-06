package com.example.demo.repository;

import com.example.demo.model.Customer;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	 Optional<Customer> findByEmail(String email);
}
