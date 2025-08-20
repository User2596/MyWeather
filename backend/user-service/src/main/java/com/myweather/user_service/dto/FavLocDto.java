package com.myweather.user_service.dto;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

public class FavLocDto {
	
	private String place;

	@DecimalMin(value = "-90.0", inclusive = true, message = "Latitude must be ≥ -90")
	@DecimalMax(value = "90.0", inclusive = true, message = "Latitude must be ≤ 90")
	private Double latitude;

	@DecimalMin(value = "-180.0", inclusive = false, message = "Longitude must be > -180")
	@DecimalMax(value = "180.0", inclusive = true, message = "Longitude must be ≤ 180")
	private Double longitude;
	
	public FavLocDto() {}

	public FavLocDto(String place, Double latitude, Double longitude) {
		this.place = place;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}

	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
}