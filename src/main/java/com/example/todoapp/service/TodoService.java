package com.example.todoapp.service;

import java.util.List;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.model.User;

public interface TodoService {
    List<TodoDto> findAllByUser(User user);
    TodoDto save(TodoDto todo, User user);
    void delete(Long id, User user);
}
