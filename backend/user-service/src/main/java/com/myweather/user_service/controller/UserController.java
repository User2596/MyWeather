package com.myweather.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweather.user_service.dto.UpdateUserRequest;
import com.myweather.user_service.model.User;
import com.myweather.user_service.service.UserService;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<User> displayUser(Authentication authentication) {
		return userService.getUser(authentication.getName());
	}
	
	@PatchMapping
	public ResponseEntity<User> updateUser(
			@RequestBody UpdateUserRequest request,
			Authentication authentication
		) {
		return userService.updateUser(authentication.getName(), request);
	}
	
	@DeleteMapping
	public ResponseEntity<User> deleteUser(Authentication authentication) {
		return userService.deleteUser(authentication.getName());
	}
}