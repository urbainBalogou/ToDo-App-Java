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
import com.example.todoapp.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
            userService.register(registrationDto);
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
        Optional<User> userOptional = userService.login(loginDto);
        
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