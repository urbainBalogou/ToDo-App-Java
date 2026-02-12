package com.example.todoapp.use_cases;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserAuthenticationService;
import com.example.todoapp.service.UserRegistrationService;
import com.example.todoapp.service.UserValidationService;

@Service
public class UserServiceUseCase implements UserRegistrationService, UserAuthenticationService {

    private final UserDao userDao;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    public UserServiceUseCase(UserDao userDao, 
                             UserValidationService userValidationService, 
                             PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userValidationService = userValidationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserRegistrationDto registrationDto) {
        // Valider les données d'inscription
        userValidationService.validateRegistrationData(registrationDto);
        
        // Vérifier si l'utilisateur existe déjà
        if (emailExists(registrationDto.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        // Créer un nouvel utilisateur
        User newUser = new User();
        newUser.setName(registrationDto.getName());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        return userDao.save(newUser);
    }

    @Override
    public Optional<User> login(UserLoginDto loginDto) {
        Optional<User> userOpt = userDao.findByEmail(loginDto.getEmail());
        System.out.println("User: " + userOpt); 
        if (userOpt.isPresent() && passwordEncoder.matches(loginDto.getPassword(), userOpt.get().getPassword())) {
            return userOpt;
        }
        
        return Optional.empty();
    }

    @Override
    public boolean emailExists(String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}