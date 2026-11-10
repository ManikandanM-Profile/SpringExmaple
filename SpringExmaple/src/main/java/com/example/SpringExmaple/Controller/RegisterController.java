package com.example.SpringExmaple.Controller;

import com.example.SpringExmaple.DTO.RegisterRequest;
import com.example.SpringExmaple.DTO.Users;
import com.example.SpringExmaple.Repository.UserRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class RegisterController {

	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public RegisterController(UserRepository repository, PasswordEncoder passwordEncoder) {

		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody RegisterRequest request) {
		System.err.println("------------------------------------------------");
		String username = request.getUsername() == null ? "" : request.getUsername().trim();

		String password = request.getPassword() == null ? "" : request.getPassword();

		int role;
		if (request.getRole() == 0)
			role = 0;
		else
			role = request.getRole();

		String email = request.getEmail() == null ? "" : request.getEmail().trim();

		String phoneNo = request.getPhoneNo() == null ? "" : request.getPhoneNo().trim();

		Users user = new Users();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setRole(role);
		user.setEmail(email);
		user.setPhoneNo(phoneNo);
		repository.save(user);

		return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	}
}
