package com.example.todoapp.use_cases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todoapp.dao.TodoDao;
import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.mapper.TodoMapper;
import com.example.todoapp.model.Todo;
import com.example.todoapp.model.User;
import com.example.todoapp.service.TodoService;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - On aurait pu mettre la logique Todo dans le contrôleur directement.
 *   - Le contrôleur aurait eu 2 responsabilités : gérer les requêtes HTTP + logique métier.
 *
 * AVEC SOLID (maintenant) :
 *   - Cette classe ne gère QUE la logique métier des Todos (CRUD).
 *   - Le contrôleur ne fait que recevoir les requêtes et déléguer.
 *
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Le contrôleur aurait pu instancier directement cette classe :
 *     new TodoServiceUseCase(todoDao, todoMapper).
 *
 * AVEC SOLID (maintenant) :
 *   - Le contrôleur dépend de TodoService (interface/abstraction).
 *   - Spring injecte automatiquement cette implémentation.
 *   - On pourrait créer un TodoServiceCacheDecorator sans toucher au contrôleur.
 *
 * ======================= PRINCIPE SOLID : L (Liskov Substitution) ========================
 *
 * - Cette classe implémente fidèlement le contrat de TodoService.
 * - Toute autre implémentation (ex: avec cache, avec audit) peut la remplacer
 *   sans que le contrôleur ne remarque la différence.
 * =========================================================================================
 */
@Service
public class TodoServiceUseCase implements TodoService {

    private final TodoDao todoDao;
    private final TodoMapper todoMapper;

    public TodoServiceUseCase(TodoDao todoDao, TodoMapper todoMapper) {
        this.todoDao = todoDao;
        this.todoMapper = todoMapper;
    }

    @Override
    public List<TodoDto> findAllByUser(User user) {
        return todoDao.findByUser(user).stream()
            .map(todoMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public TodoDto save(TodoDto todoDto, User user) {
        Todo entity = todoMapper.toEntity(todoDto);
        entity.setUser(user);
        Todo savedEntity = todoDao.save(entity);
        return todoMapper.toDto(savedEntity);
    }

    @Override
    public void delete(Long id, User user) {
        todoDao.findById(id)
            .filter(todo -> todo.getUser().getId().equals(user.getId()))
            .ifPresent(todoDao::delete);
    }
}
