package com.myweather.user_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweather.user_service.dto.FavLocDto;
import com.myweather.user_service.service.FavLocService;

@RestController
@RequestMapping("favorites")
public class FavLocController {

	@Autowired
	private FavLocService favLocService;
	
	@GetMapping
	public ResponseEntity<List<FavLocDto>> showFavorites(Authentication authentication) {
		return favLocService.getAllFavorites(authentication.getName());
	}
	
	@PostMapping
	public ResponseEntity<FavLocDto> addFavorite(
			@Valid @RequestBody FavLocDto request,
			Authentication authentication
	) {		
		return favLocService.addFavorite(authentication.getName(), request);
	}
	
	@DeleteMapping
	public ResponseEntity<List<FavLocDto>> deleteFavorites(Authentication authentication) {
		return favLocService.deleteAllFavorites(authentication.getName());
	}
	
	@GetMapping("{place}")
	public ResponseEntity<FavLocDto> displayFavorite(
			@PathVariable String place,
			Authentication authentication
	) {
		return favLocService.getFavorite(authentication.getName(), place);	
	}
	
	@PatchMapping("{place}")
	public ResponseEntity<FavLocDto> updateFavorite(
			@PathVariable String place,
			@Valid @RequestBody FavLocDto request,
			Authentication authentication
	) {
		return favLocService.updateFavorite(authentication.getName(), place, request);
	}

	@DeleteMapping("{place}")
	public ResponseEntity<FavLocDto> deleteFavorite(
			@PathVariable String place,
			Authentication authentication
	) {
		return favLocService.deleteFavorite(authentication.getName(), place);
	}
}