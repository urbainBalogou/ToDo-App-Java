/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.todoapp.service;

import java.util.List;

import com.example.todoapp.dto.TodoDto;



/**
 *
 * @author Admin
 */

public interface TodoService {
    List<TodoDto> findAll();
    TodoDto save(TodoDto todo);
    void delete(Long id);
    
}
