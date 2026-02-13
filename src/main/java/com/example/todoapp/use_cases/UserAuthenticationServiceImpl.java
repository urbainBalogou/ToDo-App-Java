package com.example.todoapp.use_cases;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserAuthenticationService;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> login(UserLoginDto loginDto) {
        if (loginDto == null
                || !StringUtils.hasText(loginDto.getEmail())
                || !StringUtils.hasText(loginDto.getPassword())) {
            return Optional.empty();
        }

        Optional<User> userOpt = userDao.findByEmail(normalizeEmail(loginDto.getEmail()));
        if (userOpt.isPresent() && passwordEncoder.matches(loginDto.getPassword(), userOpt.get().getPassword())) {
            return userOpt;
        }

        log.debug("Login failed for email={}", loginDto.getEmail());
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return Optional.empty();
        }
        return userDao.findByEmail(normalizeEmail(email));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
