package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.entity.Customer;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return "Username already exists!";
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return "Email already in use!";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully!";
    }

    public Map<String, Object> loginAndFetchCustomer(String username, String rawPassword) {
        Map<String, Object> response = new HashMap<>();
        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            String token = jwtUtil.generateToken(user.getUsername(), user.getRole(),user.getEmail());
            response.put("token", token);
            response.put("role", user.getRole());
            response.put("email", user.getEmail());

            // Check if email exists in customer table
            Customer customer = customerRepository.findByEmail(user.getEmail()).orElse(null);
            if (customer != null) {
                response.put("customerDetails", customer);
            }

            return response;
        } else {
            response.put("error", "Invalid credentials!");
            return response;
        }
    }
}
