package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) implements UserService {

    public Long getIdByEmail(String email) {
        log.debug("Email: [{}]", email);
        User byEmail = userRepo.findByEmail(email).orElseThrow(() -> {
            log.error("User with email [{}] not exist!", email);
            throw new RuntimeException("User not found!");
        });
        log.debug("Id: [{}]", byEmail);

        return byEmail.getId();
    }

    @Override
    public boolean saveUser(User user) {
        if (userRepo.findByEmail(user.getEmail()).isPresent()){
            log.warn("User with email [{}] already exist!", user.getEmail());
            return false;
        }
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepo.save(user);
        log.info("Save user: [{}]", save);
        return true;
    }

    @Override
    public boolean checkUser(User user) {
        return true;
    }
}
