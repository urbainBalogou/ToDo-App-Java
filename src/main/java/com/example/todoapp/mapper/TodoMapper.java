package com.example.todoapp.mapper;

import org.springframework.stereotype.Component;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.model.Todo;

@Component
public class TodoMapper {

    public TodoDto toDto(Todo entity) {
        if (entity == null) {
            return null;
        }
        TodoDto dto = new TodoDto();
        dto.setId(entity.getId());
        dto.setTask(entity.getTask());
        dto.setCompleted(entity.isCompleted());
        return dto;
    }

    public Todo toEntity(TodoDto dto) {
        if (dto == null) {
            return null;
        }
        Todo entity = new Todo();
        entity.setId(dto.getId());
        entity.setTask(dto.getTask());
        entity.setCompleted(Boolean.TRUE.equals(dto.getCompleted()));
        return entity;
    }
}
