package com.example.todoapp.service;

import java.util.Optional;

import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.model.User;

/*
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * SANS SOLID (avant) :
 *   - Cette interface contenait login() ET findByEmail().
 *   - Un contrôleur qui veut juste authentifier un utilisateur était obligé
 *     de connaître aussi la méthode findByEmail() dont il n'a pas besoin.
 *
 * AVEC SOLID (maintenant) :
 *   - L'interface ne contient QUE la méthode login().
 *   - findByEmail() a été déplacée dans UserQueryService (interface dédiée).
 *   - Le contrôleur ne dépend que de ce qu'il utilise réellement.
 *
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - Une seule classe UserServiceUseCase implémentait à la fois
 *     UserRegistrationService ET UserAuthenticationService.
 *   - Deux raisons de changer dans la même classe (inscription + auth).
 *
 * AVEC SOLID (maintenant) :
 *   - UserAuthenticationServiceImpl n'a qu'UNE seule responsabilité :
 *     authentifier un utilisateur.
 * ==========================================================================================
 */
public interface UserAuthenticationService {
    Optional<User> login(UserLoginDto loginDto);
}
