package com.example.budgettracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrackerApi {

    @GetMapping("/user")
    public String menu() {
        return "menu-page";
    }
}
