package com.example.todoapp.use_cases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserRegistrationService;
import com.example.todoapp.service.UserValidationService;

@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserDao userDao;
    private final UserValidationService userValidationService;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(UserDao userDao,
                                       UserValidationService userValidationService,
                                       PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userValidationService = userValidationService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserRegistrationDto registrationDto) {
        userValidationService.validateRegistrationData(registrationDto);

        if (emailExists(registrationDto.getEmail())) {
            throw new RuntimeException("Cet email est déjà utilisé !");
        }

        User newUser = new User();
        newUser.setName(registrationDto.getName().trim());
        newUser.setEmail(normalizeEmail(registrationDto.getEmail()));
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        return userDao.save(newUser);
    }

    @Override
    public boolean emailExists(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        return userDao.existsByEmail(normalizeEmail(email));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
