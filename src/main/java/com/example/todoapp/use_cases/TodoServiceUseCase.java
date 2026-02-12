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
import com.example.todoapp.model.Todo;
import com.example.todoapp.service.TodoService;

/**
 *
 * @author Admin
 */

@Service
public class TodoServiceUseCase implements TodoService {
    
     private final TodoDao todoDao;

    public TodoServiceUseCase(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    
    @Override
      public List<TodoDto> findAll() {
        return todoDao.findAll().stream()
            .map(this::convertToDto)
            .collect(Collectors.toList());
    }

    @Override
     public TodoDto save(TodoDto todoDto) {
        Todo entity = convertToEntity(todoDto);
        Todo savedEntity = todoDao.save(entity);
        return convertToDto(savedEntity);
    }
    
    @Override
    public void delete(Long id){
    
        todoDao.deleteById(id);
    }
    
    private TodoDto convertToDto(Todo entity) {
        TodoDto dto = new TodoDto();
        dto.setId(entity.getId());
        dto.setTask(entity.getTask());
        dto.setCompleted(entity.isCompleted());
        return dto;
    }

    private Todo convertToEntity(TodoDto dto) {
        Todo entity = new Todo();
        entity.setId(dto.getId());
        entity.setTask(dto.getTask());
        entity.setCompleted(dto.getCompleted() != null ? dto.getCompleted() : false);
        return entity;
    }

  
}
