package com.example.todoapp.service;

import java.util.List;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.model.User;

/*
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Le contrôleur aurait pu dépendre directement de TodoServiceUseCase (classe concrète).
 *   - Tout changement dans l'implémentation forcerait à modifier le contrôleur.
 *
 * AVEC SOLID (maintenant) :
 *   - Le contrôleur dépend de l'interface TodoService (abstraction).
 *   - On peut changer l'implémentation (ex: TodoServiceCacheDecorator)
 *     sans toucher au contrôleur.
 *
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * - L'interface ne contient que les opérations CRUD nécessaires pour les Todos.
 * - Chaque méthode prend un User en paramètre pour garantir l'isolation des données.
 * - Pas de méthodes "fourre-tout" comme findAll() sans contexte utilisateur.
 * ==========================================================================================
 */
public interface TodoService {
    List<TodoDto> findAllByUser(User user);
    TodoDto save(TodoDto todo, User user);
    void delete(Long id, User user);
}
