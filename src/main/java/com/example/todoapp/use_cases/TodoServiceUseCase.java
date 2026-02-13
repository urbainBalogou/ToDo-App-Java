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
