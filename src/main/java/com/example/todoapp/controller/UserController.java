package com.example.todoapp.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todoapp.dto.UserLoginDto;
import com.example.todoapp.dto.UserRegistrationDto;
import com.example.todoapp.model.User;
import jakarta.servlet.http.HttpSession;
import com.example.todoapp.service.UserAuthenticationService;
import com.example.todoapp.service.UserRegistrationService;

/*
 * ======================= PRINCIPE SOLID : S (Single Responsibility) =======================
 *
 * SANS SOLID (avant) :
 *   - Un seul contrôleur gérait les Todos ET les utilisateurs (inscription, login, logout).
 *   - Trop de responsabilités dans une seule classe.
 *
 * AVEC SOLID (maintenant) :
 *   - UserController gère UNIQUEMENT les opérations liées aux utilisateurs
 *     (inscription, connexion, déconnexion).
 *   - TodoController gère UNIQUEMENT les opérations liées aux tâches.
 *   - Chaque contrôleur a une seule raison de changer.
 *
 * ======================= PRINCIPE SOLID : D (Dependency Inversion) =======================
 *
 * SANS SOLID (avant) :
 *   - Le contrôleur aurait pu instancier directement les services concrets :
 *     new UserRegistrationServiceImpl(...), new UserAuthenticationServiceImpl(...)
 *
 * AVEC SOLID (maintenant) :
 *   - Le contrôleur dépend des INTERFACES (UserRegistrationService, UserAuthenticationService).
 *   - Il ne connaît PAS les classes concrètes → couplage faible.
 *   - Spring injecte les implémentations via le constructeur (injection par constructeur).
 *
 * ======================= PRINCIPE SOLID : I (Interface Segregation) =======================
 *
 * - Le contrôleur n'utilise que login() de UserAuthenticationService.
 * - Il n'utilise que register() de UserRegistrationService.
 * - Il ne voit PAS findByEmail() ou emailExists() → interfaces épurées.
 * ==========================================================================================
 */
@Controller
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserAuthenticationService userAuthenticationService;

    public UserController(UserRegistrationService userRegistrationService, 
                         UserAuthenticationService userAuthenticationService) {
        this.userRegistrationService = userRegistrationService;
        this.userAuthenticationService = userAuthenticationService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationDto registrationDto, 
                          RedirectAttributes redirectAttributes) {
        try {
            userRegistrationService.register(registrationDto);
            redirectAttributes.addFlashAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            return "redirect:/login";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("user", new UserLoginDto());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") UserLoginDto loginDto, 
                       HttpSession session,
                       RedirectAttributes redirectAttributes) {
        Optional<User> userOptional = userAuthenticationService.login(loginDto);
        
        if (userOptional.isPresent()) {
            session.setAttribute("currentUser", userOptional.get());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("error", "Email ou mot de passe incorrect");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("success", "Déconnexion réussie");
        return "redirect:/login";
    }
}