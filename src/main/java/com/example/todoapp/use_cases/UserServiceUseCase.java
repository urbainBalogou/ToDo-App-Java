package com.example.todoapp.use_cases;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserService;

@Service
public class UserServiceUseCase implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserServiceUseCase(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserRegistrationDto registrationDto) {
        // Vérifier si les mots de passe correspondent
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())) {
            throw new RuntimeException("Les mots de passe ne correspondent pas !");
        }

        // Check if user already exists
        if (userDao.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        // Create new user
        User newUser = new User();
        newUser.setName(registrationDto.getName());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        return userDao.save(newUser);
    }

    @Override
    public Optional<User> login(UserLoginDto loginDto) {
        Optional<User> userOpt = userDao.findByEmail(loginDto.getEmail());
        
        if (userOpt.isPresent() && passwordEncoder.matches(loginDto.getPassword(), userOpt.get().getPassword())) {
            return userOpt;
        }
        
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public boolean emailExists(String email) {
        return userDao.existsByEmail(email);
    }
}