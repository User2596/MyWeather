package com.myweather.user_service.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	
	@Column(nullable = false, updatable = false)
	private Instant createdAt;
	private Instant lastLoginAt;
	private Instant lastUpdatedAt;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FavLocations> favLocations = new ArrayList<>();

	public User() {}

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}
	public Instant getLastLoginAt() {
		return lastLoginAt;
	}
	public Instant getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public List<FavLocations> getFavLocations() {
		return favLocations;
	}
	public void setFavLocations(List<FavLocations> favLocations) {
		this.favLocations = favLocations;
	}
	
	@PrePersist
    protected void onCreate() {
        Instant now = Instant.now();
        this.createdAt = now;
        this.lastUpdatedAt = now;
        this.lastLoginAt = now;
    }
    @PreUpdate
    protected void onUpdate() {
        this.lastUpdatedAt = Instant.now();
    }
    public void updateLastLogin() {
        this.lastLoginAt = Instant.now();
    }
}
