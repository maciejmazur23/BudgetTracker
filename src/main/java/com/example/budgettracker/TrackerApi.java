package com.example.budgettracker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TrackerApi {

    @GetMapping("/")
    public String get(Principal principal, Model model){
        return "hello";
    }
}
