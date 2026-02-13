package com.example.todoapp.mapper;

import org.springframework.stereotype.Component;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.model.Todo;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - La conversion Entity <-> DTO aurait été faite directement dans le service
 *     ou pire, dans le contrôleur.
 *   - Le service aurait eu 2 responsabilités : logique métier + conversion.
 *
 * AVEC SOLID (maintenant) :
 *   - TodoMapper a UNE seule responsabilité : convertir entre Todo et TodoDto.
 *   - Si la structure de l'entité ou du DTO change, on ne touche QUE cette classe.
 *   - Le service et le contrôleur ne sont pas impactés.
 *
 * ======================= PRINCIPE SOLID : O (Open/Closed) ================================
 *
 * - Si on ajoute un champ à Todo (ex: priority), on adapte le mapper
 *   sans modifier la logique métier du service ni le contrôleur.
 * =========================================================================================
 */
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
