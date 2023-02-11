package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepo userRepo;

    public Long getIdByEmail(String email) {
        User byEmail = userRepo.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("User not found!");
        });

        return byEmail.getId();
    }
}
