package com.example.todoapp.use_cases;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.exception.ValidationException;
import com.example.todoapp.service.UserValidationService;

@Service
public class DefaultUserValidationService implements UserValidationService {
    @Override
    public void validateRegistrationData(UserRegistrationDto registrationDto){
        if (registrationDto == null) {
            throw new ValidationException("Données d'inscription invalides !");
        }

        if (!StringUtils.hasText(registrationDto.getName())) {
            throw new ValidationException("Le champ nom est requis !");
        }

        if (!StringUtils.hasText(registrationDto.getEmail())) {
            throw new ValidationException("Le champ email est requis !");
        }

        if (!StringUtils.hasText(registrationDto.getPassword())) {
            throw new ValidationException("Le champ mot de passe est requis !");
        }

        if (!StringUtils.hasText(registrationDto.getConfirmPassword())) {
            throw new ValidationException("La confirmation du mot de passe est requise !");
        }

        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())){
            throw new ValidationException("Les mots de passe ne correspondent pas !");
        }
    }

}
