package org.example.tradingbackend.repository;

//package com.example.tradingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.tradingbackend.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}

