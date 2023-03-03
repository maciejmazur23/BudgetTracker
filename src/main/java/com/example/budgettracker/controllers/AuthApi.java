package com.example.budgettracker.controllers;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
public class AuthApi {
    private final UserService userService;

    public AuthApi(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/auth/register")
    public String register(Model model){
        model.addAttribute("user", new User());
        return "register-page";
    }

    @GetMapping("/auth/register-error")
    public String registerError(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("registerError", true);
        return "register-page";
    }

    @PostMapping("/auth/register")
    public String register(User user){
        log.info("User: [{}]", user);
        boolean isSaved = userService.saveUser(user);
        if (isSaved){
            return "redirect:/auth/login";
        }else{
            return "redirect:/auth/register-error";
        }
    }

    @GetMapping("/auth/login")
    public String login(){
        return "login-page";
    }

    @GetMapping("/auth/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login-page";
    }

}
