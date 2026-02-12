package com.example.todoapp.service;

import org.springframework.stereotype.Service;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;

@Service
public interface UserRegistrationService {
    User register(UserRegistrationDto registrationDto);
    boolean emailExists(String email);
}