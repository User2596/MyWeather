package com.myweather.user_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweather.user_service.dto.UpdateUserRequest;
import com.myweather.user_service.model.User;
import com.myweather.user_service.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public Optional<User> getAuthenticatedUser(String email) {
        return userRepo.findByEmail(email);
    }
	
	public ResponseEntity<User> getUser(String email) {
    	Optional<User> user = getAuthenticatedUser(email);
        if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
	}
	
	public ResponseEntity<User> deleteUser(String email) {
		Optional<User> user = getAuthenticatedUser(email);
        if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
        
        User response = user.get();
		userRepo.delete(user.get());
        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	public ResponseEntity<User> updateUser(String email, UpdateUserRequest request) {
		Optional<User> optUser = getAuthenticatedUser(email);
        if (optUser.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
        
        User user = optUser.get();
        if (request.getName() != null) {
			user.setName(request.getName());
		}
		if (request.getNewPassword() != null) {
			if (request.getOldPassword() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
			if (!passwordEncoder.matches(
					request.getOldPassword(),
					user.getPassword()
				)) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
			}
			user.setPassword(passwordEncoder.encode(request.getNewPassword()));
		}
        
		userRepo.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
