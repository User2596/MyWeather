package com.myweather.user_service.controller;

import com.myweather.user_service.dto.UserDto;
import com.myweather.user_service.model.User;
import com.myweather.user_service.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping
	public ResponseEntity<UserDto> register(@RequestBody User user) {
		User saved = userService.register(user);
		UserDto dto = toDto(saved);
		return ResponseEntity.created(URI.create("/api/users/" + saved.getUid())).body(dto);
	}

	@GetMapping
	public List<UserDto> list() {
		return userService.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable("id") Long id) {
		return userService.findById(id)
				.map(u -> ResponseEntity.ok(toDto(u)))
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	private UserDto toDto(User u) {
		return new UserDto(u.getUid(), u.getEmail(), u.getAddress(), u.getProfilePicture(), u.getDateOfBirth());
	}
}
