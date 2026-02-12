/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.service.TodoService;

/**
 *
 * @ author Admin
 */
@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model) {
            model.addAttribute("todos", todoService.findAll());
            model.addAttribute("newTodo", new TodoDto());
            return "index";
    }
    
    @PostMapping("/add")
    public String add(@ModelAttribute("newTodo") TodoDto newTodo) {
        
            if (newTodo != null && newTodo.getTask() != null && !newTodo.getTask().trim().isEmpty()) {
                todoService.save(newTodo);
            }
            return "redirect:/";
       
    }
    
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
            todoService.delete(id);
            return "redirect:/";
    }
}