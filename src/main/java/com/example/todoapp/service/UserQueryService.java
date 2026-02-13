package com.example.todoapp.service;

import java.util.Optional;

import com.example.todoapp.model.User;

/*
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * SANS SOLID (avant) :
 *   - Les méthodes findByEmail() et emailExists() étaient mélangées
 *     dans UserAuthenticationService et UserRegistrationService.
 *   - Un client qui veut juste chercher un utilisateur par email devait
 *     dépendre d'une interface d'authentification ou d'inscription.
 *
 * AVEC SOLID (maintenant) :
 *   - On crée une interface dédiée UserQueryService pour les opérations
 *     de lecture/recherche sur les utilisateurs.
 *   - Chaque interface a une seule raison d'exister :
 *       UserRegistrationService  -> inscrire un utilisateur
 *       UserAuthenticationService -> authentifier un utilisateur
 *       UserQueryService          -> rechercher / vérifier un utilisateur
 *   - Les clients ne dépendent que de l'interface dont ils ont besoin.
 * ==========================================================================================
 */
public interface UserQueryService {
    Optional<User> findByEmail(String email);
    boolean emailExists(String email);
}
