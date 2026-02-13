package com.example.todoapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.todoapp.dto.TodoDto;
import com.example.todoapp.model.User;
import com.example.todoapp.service.TodoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("todos", todoService.findAllByUser(currentUser));
        model.addAttribute("newTodo", new TodoDto());
        model.addAttribute("userName", currentUser.getName());
        return "index";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("newTodo") TodoDto newTodo, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        if (newTodo != null && newTodo.getTask() != null && !newTodo.getTask().trim().isEmpty()) {
            todoService.save(newTodo, currentUser);
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            return "redirect:/login";
        }
        todoService.delete(id, currentUser);
        return "redirect:/";
    }
}
