package com.example.todoapp.service;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;

public interface UserRegistrationService {
    User register(UserRegistrationDto registrationDto);
    boolean emailExists(String email);
}
