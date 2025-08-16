package com.myweather.user_service.repository;


import com.myweather.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(Long phoneNumber);

    Optional<User> findByUid(Long uid);

    Optional<User> findByProfilePicture(String profilePicture);

    Optional<User> findByDateOfBirth(LocalDate dateOfBirth);

    Optional<User> findByAddress(String address);

}
