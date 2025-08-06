package com.example.demo.service.impl;

import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private final CustomerRepository customerRepository;
//	@Autowired
//	private CustomerService customservice;


	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Optional<Customer> getCustomerById(int id) {
		return customerRepository.findById(id);
	}

	@Override
	public Optional<Customer> getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}

	@Override
	public Optional<Customer> getCustomerBycustId(int custId) {
		// TODO Auto-generated method stub
		return getCustomerBycustId(custId);
	}

	@Override
	public Object getCustIdByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.map(Customer::getCustId).orElse(null);
	}

	


}
