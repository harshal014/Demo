package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.CustomerService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;

	@PostMapping("/signup")
	public ResponseEntity<String> register(@RequestBody User user) {
		String response = userService.register(user);
		if (response.equals("User registered successfully!")) {
			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body(response);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		Map<String, Object> result = userService.loginAndFetchCustomer(user.getUsername(), user.getPassword());
		String email = (String) result.get("email");
		if (result.containsKey("error")) {
			return ResponseEntity.status(401).body(result.get("error"));
		}

		Map<String, Object> response = new HashMap<>();
		response.put("custId", customerService.getCustIdByEmail(email));

		response.put("token", result.get("token"));
		response.put("role", result.get("role"));

		// If customer details are available, include them
		if (result.containsKey("customerDetails")) {
			response.put("customer", result.get("customerDetails"));
		}

		return ResponseEntity.ok(response);
	}
}
