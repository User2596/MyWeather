package com.myweather.user_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myweather.user_service.model.FavLocations;

@Repository
public interface FavLocRepository extends JpaRepository<FavLocations, Long> {

	List<FavLocations> findByUserId(Long userId);
	Optional<FavLocations> findByUserIdAndPlace(Long userId, String place);
	
}
