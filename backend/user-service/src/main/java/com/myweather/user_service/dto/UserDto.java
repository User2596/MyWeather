package com.myweather.user_service.dto;

import java.time.LocalDate;

public class UserDto {
    private Long uid;
    private String email;
    private String address;
    private String profilePicture;
    private LocalDate dateOfBirth;

    public UserDto() {}

    public UserDto(Long uid, String email, String address, String profilePicture, LocalDate dateOfBirth) {
        this.uid = uid;
        this.email = email;
        this.address = address;
        this.profilePicture = profilePicture;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getUid() { return uid; }
    public void setUid(Long uid) { this.uid = uid; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getProfilePicture() { return profilePicture; }
    public void setProfilePicture(String profilePicture) { this.profilePicture = profilePicture; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
}
