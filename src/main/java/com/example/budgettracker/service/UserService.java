package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import org.springframework.stereotype.Service;

@Service
public record UserService(UserRepo userRepo) {

    public Long getIdByEmail(String email) {
        User byEmail = userRepo.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("User not found!");
        });

        return byEmail.getId();
    }
}
