package com.myweather.user_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myweather.user_service.dto.LoginRequest;
import com.myweather.user_service.dto.LoginResponse;
import com.myweather.user_service.dto.RegisterRequest;
import com.myweather.user_service.dto.RegisterResponse;
import com.myweather.user_service.service.AuthService;

@RestController
public class AuthController {
	
	@Autowired
	private AuthService authService;
	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("register")
	public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
		try {
            RegisterResponse response = authService.register(request);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            log.warn("Registration failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
	}
	
	@PostMapping("login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		try {
            LoginResponse response = authService.login(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException ex) {
            log.warn("Login failed: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
	}
}
