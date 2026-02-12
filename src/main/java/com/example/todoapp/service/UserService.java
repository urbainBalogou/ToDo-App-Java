package com.example.todoapp.service;

import java.util.Optional;

import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;


public interface UserService {
    User register(UserRegistrationDto registrationDto);
    Optional<User> login(UserLoginDto loginDto);
    Optional<User> findByEmail(String email);
    boolean emailExists(String email);
}