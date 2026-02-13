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

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - Un seul contrôleur "FatController" aurait géré les Todos, les utilisateurs,
 *     la validation, le tout dans une seule classe de 200+ lignes.
 *
 * AVEC SOLID (maintenant) :
 *   - Ce contrôleur ne gère QUE les requêtes HTTP liées aux Todos.
 *   - Toute la logique métier est déléguée à TodoService.
 *   - Une seule raison de changer : si les routes/endpoints Todo évoluent.
 *
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Le contrôleur aurait pu appeler directement TodoDao (couche d'accès aux données).
 *   - Couplage fort entre la présentation et la persistance.
 *
 * AVEC SOLID (maintenant) :
 *   - Le contrôleur dépend de TodoService (abstraction/interface).
 *   - Il ne sait PAS si les données viennent de H2, MySQL, ou d'un cache.
 *   - On peut changer la source de données sans toucher au contrôleur.
 * ==========================================================================================
 */
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
