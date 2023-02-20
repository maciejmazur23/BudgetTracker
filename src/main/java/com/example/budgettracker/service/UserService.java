package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record UserService(UserRepo userRepo) {

    public Long getIdByEmail(String email) {
        log.debug("Email: [{}]", email);
        User byEmail = userRepo.findByEmail(email).orElseThrow(() -> {
            throw new RuntimeException("User not found!");
        });
        log.debug("Id: [{}]", byEmail);

        return byEmail.getId();
    }
}
