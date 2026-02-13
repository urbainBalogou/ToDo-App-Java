package com.example.todoapp.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todoapp.model.User;

/*
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Les services auraient pu faire des requêtes SQL directement (JDBC, EntityManager).
 *   - Couplage fort avec la base de données H2 / SQL.
 *
 * AVEC SOLID (maintenant) :
 *   - UserDao est une abstraction : les services ne savent pas comment les données
 *     sont stockées (H2, MySQL, PostgreSQL...).
 *   - Les modules de haut niveau (services) ne dépendent pas des modules de bas niveau
 *     (implémentation JPA). Les deux dépendent d'abstractions (cette interface).
 *
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * - findByEmail() : utilisé par UserQueryServiceImpl et UserAuthenticationServiceImpl.
 * - existsByEmail() : utilisé par UserQueryServiceImpl pour vérifier les doublons.
 * - Chaque méthode a un objectif clair et précis.
 * =========================================================================================
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}