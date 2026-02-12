package com.example.todoapp.use_cases;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserService;

@Service
public class UserServiceUseCase implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User register(UserRegistrationDto registrationDto) {
        // Check if user already exists
        if (userDao.existsByEmail(registrationDto.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        // Create new user
        User newUser = new User();
        newUser.setName(registrationDto.getName());
        newUser.setEmail(registrationDto.getEmail());
        newUser.setPassword(registrationDto.getPassword());

        return userDao.save(newUser);
    }

    @Override
    public Optional<User> login(UserLoginDto loginDto) {
        Optional<User> userOpt = userDao.findByEmail(loginDto.getEmail());
        
        if (userOpt.isPresent() && userOpt.get().getPassword().equals(loginDto.getPassword())) {
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