package com.example.todoapp.service;

import com.example.todoapp.dto.UserRegistrationDto;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - La validation était faite directement dans la méthode register()
 *     du service d'inscription. Si les règles de validation changent,
 *     il faut modifier le service d'inscription.
 *
 * AVEC SOLID (maintenant) :
 *   - La validation est extraite dans sa propre interface/classe.
 *   - Si les règles changent (ex: longueur minimale du mot de passe),
 *     on ne touche QUE DefaultUserValidationService.
 *
 * ======================= PRINCIPE SOLID : O (Open/Closed) ================================
 *
 * - Grâce à l'interface, on peut créer une nouvelle implémentation
 *   (ex: StrictUserValidationService) sans modifier le code existant.
 * - On remplace simplement le bean Spring injecté.
 * =========================================================================================
 */
public interface UserValidationService {
    void validateRegistrationData(UserRegistrationDto registrationDto);
}
