package com.example.todoapp.use_cases;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.exception.EmailAlreadyExistsException;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserQueryService;
import com.example.todoapp.service.UserRegistrationService;
import com.example.todoapp.service.UserValidationService;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - Une seule classe "UserServiceUseCase" gérait l'inscription ET l'authentification.
 *   - Si on modifie la logique d'inscription, on risque de casser l'authentification.
 *
 * AVEC SOLID (maintenant) :
 *   - Cette classe ne gère QUE l'inscription d'un utilisateur.
 *   - Une seule raison de changer : si la logique d'inscription évolue.
 *
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - La vérification emailExists() était codée en dur dans cette classe.
 *   - La validation était aussi faite directement ici.
 *
 * AVEC SOLID (maintenant) :
 *   - On dépend de UserQueryService (abstraction) pour vérifier l'email.
 *   - On dépend de UserValidationService (abstraction) pour la validation.
 *   - On peut remplacer ces implémentations sans toucher ce code.
 *
 * ======================= PRINCIPE SOLID : O (Open/Closed) ================================
 *
 * - Si on veut ajouter une étape à l'inscription (ex: envoyer un email de confirmation),
 *   on peut décorer cette classe ou en créer une nouvelle implémentation,
 *   sans modifier le code existant.
 * =========================================================================================
 */
@Service
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private final UserDao userDao;
    private final UserValidationService userValidationService;
    private final UserQueryService userQueryService;
    private final PasswordEncoder passwordEncoder;

    public UserRegistrationServiceImpl(UserDao userDao,
                                       UserValidationService userValidationService,
                                       UserQueryService userQueryService,
                                       PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userValidationService = userValidationService;
        this.userQueryService = userQueryService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(UserRegistrationDto registrationDto) {
        // S : Délègue la validation à UserValidationService (SRP)
        userValidationService.validateRegistrationData(registrationDto);

        // D : Utilise UserQueryService (abstraction) pour vérifier l'existence
        if (userQueryService.emailExists(registrationDto.getEmail())) {
            throw new EmailAlreadyExistsException("Cet email est déjà utilisé !");
        }

        User newUser = new User();
        newUser.setName(registrationDto.getName().trim());
        newUser.setEmail(normalizeEmail(registrationDto.getEmail()));
        newUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        return userDao.save(newUser);
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
