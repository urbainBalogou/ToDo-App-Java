package com.example.todoapp.service;

import org.springframework.stereotype.Service;

import com.example.todoapp.dto.UserRegistrationDto;


@Service
public interface UserValidationService {
    void validateRegistrationData(UserRegistrationDto registrationDto);
}