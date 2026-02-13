package com.example.todoapp.use_cases;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.exception.ValidationException;
import com.example.todoapp.service.UserValidationService;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - La validation était mélangée dans register() du service d'inscription.
 *   - Pour changer une règle de validation, il fallait toucher au service d'inscription.
 *
 * AVEC SOLID (maintenant) :
 *   - Cette classe n'a qu'UNE seule responsabilité : valider les données d'inscription.
 *   - Si on ajoute une règle (ex: email format, mot de passe min 8 caractères),
 *     on ne modifie QUE cette classe.
 *
 * ======================= PRINCIPE SOLID : O (Open/Closed) ================================
 *
 * SANS SOLID (avant) :
 *   - Pour changer les règles de validation, on devait modifier le code existant.
 *
 * AVEC SOLID (maintenant) :
 *   - "Default" dans le nom indique que c'est l'implémentation par défaut.
 *   - On peut créer StrictUserValidationService (ex: mot de passe 12 chars + majuscule)
 *     et remplacer le bean dans la config Spring, SANS modifier cette classe.
 *
 * ======================= PRINCIPE SOLID : L (Liskov Substitution) ========================
 *
 * - Toute implémentation de UserValidationService peut remplacer celle-ci
 *   sans casser le comportement de UserRegistrationServiceImpl.
 * =========================================================================================
 */
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
