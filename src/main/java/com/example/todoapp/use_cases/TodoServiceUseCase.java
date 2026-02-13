/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todoapp.use_cases;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.todoapp.dao.TodoDao;
import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.mapper.TodoMapper;
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;

/**
 *
 * @author Admin
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
      public List<TodoDto> findAll() {
        return todoDao.findAll().stream()
            .map(todoMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
     public TodoDto save(TodoDto todoDto) {
        Todo entity = todoMapper.toEntity(todoDto);
        Todo savedEntity = todoDao.save(entity);
        return todoMapper.toDto(savedEntity);
    }
    
    @Override
    public void delete(Long id){
    
        todoDao.deleteById(id);
    }
    
  
}
