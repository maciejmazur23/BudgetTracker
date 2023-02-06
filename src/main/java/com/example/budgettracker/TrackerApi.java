package com.example.budgettracker;

import com.example.budgettracker.model.OPERATION;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class TrackerApi {

    @GetMapping("/operations")
    public String get(Principal principal, Model model){
        model.addAttribute("newOperation", new Operation());
        return "custom";
    }

    @PostMapping("/add-operation")
    public String addTransaction(@ModelAttribute("newOperation") Operation operation){
        System.out.println(operation);
        return "custom";
    }

}
