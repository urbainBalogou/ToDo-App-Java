package com.example.todoapp.use_cases;

import org.springframework.stereotype.Service;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.service.UserValidationService;

@Service
public class DefaultUserValidationService implements UserValidationService {
    @Override
    public void validateRegistrationData(UserRegistrationDto registrationDto){
        if (!registrationDto.getPassword().equals(registrationDto.getConfirmPassword())){
            throw new RuntimeException("Les mots de passe ne correspondent pas !");
        }

        if(!(registrationDto.getName() != null || registrationDto.getName().trim().isEmpty())){
            throw new RuntimeException("Le champ nom est requis !");
        }

        
        if(!(registrationDto.getEmail() != null || registrationDto.getEmail().trim().isEmpty())){
            throw new RuntimeException("Le champ email est requis !");
        }
    }

}
