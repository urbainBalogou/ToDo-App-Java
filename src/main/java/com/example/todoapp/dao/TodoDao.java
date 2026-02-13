package com.example.todoapp.dao;

import java.util.List;

import com.example.todoapp.model.Todo;
import com.example.todoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Le service aurait pu utiliser directement EntityManager ou JDBC
 *     pour accéder aux données → couplage fort avec la technologie de persistance.
 *
 * AVEC SOLID (maintenant) :
 *   - TodoDao est une INTERFACE (abstraction) qui étend JpaRepository.
 *   - Le service dépend de cette abstraction, pas de l'implémentation Hibernate.
 *   - Spring Data génère l'implémentation automatiquement.
 *   - Si on migre vers MongoDB, on change juste l'interface sans toucher au service.
 *
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * - findByUser() et findByUserId() : méthodes spécifiques aux besoins de l'app.
 * - On n'expose pas de méthodes inutiles, uniquement ce qui est nécessaire
 *   en plus de ce que JpaRepository fournit déjà.
 * =========================================================================================
 */
@Repository
public interface TodoDao extends JpaRepository<Todo, Long>  {
    List<Todo> findByUser(User user);
    List<Todo> findByUserId(Long userId);
}