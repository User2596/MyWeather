package com.myweather.user_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myweather.user_service.dto.FavLocDto;
import com.myweather.user_service.model.FavLocations;
import com.myweather.user_service.model.User;
import com.myweather.user_service.repository.FavLocRepository;

@Service
public class FavLocService {

	@Autowired
    private FavLocRepository favLocRepo;
    
	@Autowired
	private UserService userService;

    public ResponseEntity<List<FavLocDto>> getAllFavorites(String email) {
    	Optional<User> user = userService.getAuthenticatedUser(email);
        if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		List<FavLocations> favLocList = favLocRepo.findByUserId(user.get().getId());
		List<FavLocDto> response = new ArrayList<>();
		if (favLocList.size() == 0) {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
		for (FavLocations favLoc: favLocList) {
			response.add(new FavLocDto(
					favLoc.getPlace(),
					favLoc.getLatitude(),
					favLoc.getLongitude()
			));
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<FavLocDto> getFavorite(String email, String place) {
    	Optional<User> user = userService.getAuthenticatedUser(email);
		if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		Optional<FavLocations> optFavLoc =
				favLocRepo.findByUserIdAndPlace(user.get().getId(), place);
		if (optFavLoc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		FavLocations favLoc = optFavLoc.get();
		FavLocDto response =
				new FavLocDto(
						favLoc.getPlace(),
						favLoc.getLatitude(),
						favLoc.getLongitude()
				);
		return new ResponseEntity<>(response, HttpStatus.OK);	
    }

    public ResponseEntity<FavLocDto> addFavorite(String email, FavLocDto request) {
        Optional<User> user = userService.getAuthenticatedUser(email);        
        if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		if(favLocRepo.findByUserIdAndPlace(
				user.get().getId(),
				request.getPlace()).isPresent()
		) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		
		FavLocations favLoc =
				new FavLocations(
						user.get(),
						request.getPlace(),
						request.getLatitude(),
						request.getLongitude()
				);
		favLocRepo.save(favLoc);
		return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

    public ResponseEntity<List<FavLocDto>> deleteAllFavorites(String email) {
        Optional<User> user = userService.getAuthenticatedUser(email);
        if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		List<FavLocations> favLocList = favLocRepo.findByUserId(user.get().getId());
		List<FavLocDto> response = new ArrayList<>();
		if (favLocList.size() == 0) {
			return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
		}
		
		for (FavLocations favLoc: favLocList) {
			response.add(new FavLocDto(
					favLoc.getPlace(),
					favLoc.getLatitude(),
					favLoc.getLongitude()
			));
			favLocRepo.delete(favLoc);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<FavLocDto> deleteFavorite(String email, String place) {
    	Optional<User> user = userService.getAuthenticatedUser(email);
    	if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		Optional<FavLocations> optFavLoc =
				favLocRepo.findByUserIdAndPlace(user.get().getId(), place);
		if (optFavLoc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		FavLocations favLoc = optFavLoc.get();
		FavLocDto response =
				new FavLocDto(
						favLoc.getPlace(),
						favLoc.getLatitude(),
						favLoc.getLongitude()
				);
		favLocRepo.delete(favLoc);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<FavLocDto> updateFavorite(String email, String place, FavLocDto request) {
        Optional<User> user = userService.getAuthenticatedUser(email);
        if (user.isEmpty()) {
		    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
		Optional<FavLocations> optFavLoc =
				favLocRepo.findByUserIdAndPlace(user.get().getId(), place);
		if (optFavLoc.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		FavLocations favLoc = optFavLoc.get();
		if (request.getPlace() != null) {
			favLoc.setPlace(request.getPlace());
		}
		if (request.getLatitude() != null) {
			favLoc.setLatitude(request.getLatitude());
		}
		if (request.getLongitude() != null) {
			favLoc.setLongitude(request.getLongitude());
		}
		
		favLocRepo.save(favLoc);
		FavLocDto response =
				new FavLocDto(
						favLoc.getPlace(),
						favLoc.getLatitude(),
						favLoc.getLongitude()
				);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}