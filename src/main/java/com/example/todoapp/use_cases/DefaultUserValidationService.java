package com.example.todoapp.use_cases;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.service.UserValidationService;

@Service
public class DefaultUserValidationService implements UserValidationService {
    @Override
    public void validateRegistrationData(UserRegistrationDto registrationDto){
        if (registrationDto == null) {
            throw new RuntimeException("Données d'inscription invalides !");
        }

        if (!StringUtils.hasText(registrationDto.getName())) {
            throw new RuntimeException("Le champ nom est requis !");
        }

        if (!StringUtils.hasText(registrationDto.getEmail())) {
            throw new RuntimeException("Le champ email est requis !");
        }

        if (!StringUtils.hasText(registrationDto.getPassword())) {
            throw new RuntimeException("Le champ mot de passe est requis !");
        }

        if (!StringUtils.hasText(registrationDto.getConfirmPassword())) {
            throw new RuntimeException("La confirmation du mot de passe est requise !");
        }

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())){
            throw new RuntimeException("Les mots de passe ne correspondent pas !");
        }
    }

}
