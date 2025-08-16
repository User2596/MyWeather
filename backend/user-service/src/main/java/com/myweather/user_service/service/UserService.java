package com.myweather.user_service.service;

import com.myweather.user_service.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User register(User user);
    Optional<User> findById(Long uid);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}