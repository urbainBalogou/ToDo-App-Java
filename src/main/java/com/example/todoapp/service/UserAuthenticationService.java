package com.example.todoapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.model.User;


@Service
public interface UserAuthenticationService {
    Optional<User> login(UserLoginDto loginDto);
    Optional<User> findByEmail(String email);
}
