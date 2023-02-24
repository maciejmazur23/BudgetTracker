package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthApi {
    private final UserService userService;

    @GetMapping("/auth/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register-page";
    }

    @PostMapping("/auth/register")
    public String register(User user){
        log.info("User: [{}]", user);
        boolean isSaved = userService.saveUser(user);
        if (isSaved){
            return "redirect:/auth/login";
        }return "redirect:/auth/register";
    }

    @GetMapping("/auth/login")
    public String login(){
        return "login-page";
    }
}
