package com.example.todoapp.service;

import com.example.todoapp.dto.UserRegistrationDto;

public interface UserValidationService {
    void validateRegistrationData(UserRegistrationDto registrationDto);
}
