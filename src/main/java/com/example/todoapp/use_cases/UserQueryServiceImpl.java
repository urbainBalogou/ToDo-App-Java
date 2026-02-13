package com.example.todoapp.use_cases;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.todoapp.dao.UserDao;
import com.example.todoapp.model.User;
import com.example.todoapp.service.UserQueryService;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - Les méthodes findByEmail() et emailExists() étaient dispersées dans
 *     UserAuthenticationServiceImpl et UserRegistrationServiceImpl.
 *   - Chaque classe avait des responsabilités de LECTURE en plus de sa
 *     responsabilité principale (authentification ou inscription).
 *
 * AVEC SOLID (maintenant) :
 *   - Cette classe centralise TOUTES les opérations de recherche/vérification
 *     sur les utilisateurs.
 *   - Une seule responsabilité : requêter les données utilisateur.
 *   - Les autres services l'utilisent via l'interface UserQueryService (DIP).
 *
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * - UserRegistrationServiceImpl dépend de UserQueryService (abstraction),
 *   pas de UserQueryServiceImpl (implémentation concrète).
 * ==========================================================================================
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserDao userDao;

    public UserQueryServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        if (!StringUtils.hasText(email)) {
            return Optional.empty();
        }
        return userDao.findByEmail(normalizeEmail(email));
    }

    @Override
    public boolean emailExists(String email) {
        if (!StringUtils.hasText(email)) {
            return false;
        }
        return userDao.existsByEmail(normalizeEmail(email));
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase();
    }
}
