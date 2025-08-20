package com.myweather.user_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.myweather.user_service.dto.LoginRequest;
import com.myweather.user_service.dto.LoginResponse;
import com.myweather.user_service.dto.RegisterRequest;
import com.myweather.user_service.dto.RegisterResponse;
import com.myweather.user_service.model.User;
import com.myweather.user_service.repository.UserRepository;

@Service
public class AuthService {
    
	@Autowired
	private UserRepository userRepository;
    
	@Autowired
	private PasswordEncoder passwordEncoder;
    
	@Autowired
	private JwtService jwtService;
	private Logger log = LoggerFactory.getLogger(AuthService.class);

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        
		User user = new User(
				request.getName(),
				request.getEmail(),
				passwordEncoder.encode(request.getPassword())
		);
		userRepository.save(user);
		
		String token = jwtService.generateJwt(user.getEmail());
		log.info("New user registered: {}", user.getEmail());
	    return new RegisterResponse(user.getName(), user.getEmail(), token);
    }

    public LoginResponse login(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
        		.filter(user -> passwordEncoder.matches(
        				request.getPassword(),
        				user.getPassword()
        		)).map(user -> {
        			user.updateLastLogin();
        			userRepository.save(user);
					String token = jwtService.generateJwt(user.getEmail());
					log.info("Login success: {}", user.getEmail());
					return new LoginResponse(user.getEmail(), token);
				})
        		.orElseThrow(() -> new IllegalArgumentException("Invalid credentials")
		);
    }
}
