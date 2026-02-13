package com.example.todoapp.service;

import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;

/*
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * SANS SOLID (avant) :
 *   - Cette interface contenait register() ET emailExists().
 *   - emailExists() est une opération de LECTURE, pas d'inscription.
 *   - Un client qui veut juste inscrire un utilisateur voyait une méthode
 *     de vérification dont il n'a pas besoin.
 *
 * AVEC SOLID (maintenant) :
 *   - L'interface ne contient QUE la méthode register().
 *   - emailExists() a été déplacée dans UserQueryService.
 *   - Chaque interface = un contrat clair et minimal.
 *
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * - Une seule responsabilité : inscrire un utilisateur.
 * - L'implémentation (UserRegistrationServiceImpl) utilise UserQueryService
 *   en interne pour vérifier si l'email existe, sans exposer cela dans le contrat.
 * ==========================================================================================
 */
public interface UserRegistrationService {
    User register(UserRegistrationDto registrationDto);
}
