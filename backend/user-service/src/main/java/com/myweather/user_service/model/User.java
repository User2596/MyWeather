package com.myweather.user_service.model;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

@Entity
@Table(name = "users", indexes = {
    @Index(columnList = "email", unique = true),
    @Index(columnList = "username", unique = true)
})
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String email;
    private String password;
    private Long phoneNumber;
    private String address;
    private String profilePicture;
    private LocalDate dateOfBirth;

    // No-arg constructor required by JPA
    public User() {}

    public User(Long uid, String email, String password, Long phoneNumber, String address, String profilePicture, LocalDate dateOfBirth) {
        this.uid = uid;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.profilePicture = profilePicture;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters
    public Long getUid() {
        return uid;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    //Setters

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
