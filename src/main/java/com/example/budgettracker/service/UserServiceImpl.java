package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;
import com.example.budgettracker.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Override
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
        boolean matches = Pattern.compile(REGEX_PATTERN).matcher(user.getEmail()).matches();

        if (!matches) {
            log.warn("Email [{}] is out of pattern!", user.getEmail());
            return false;
        }

        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            log.warn("User with email [{}] already exist!", user.getEmail());
            return false;
        }

        User save = executeSaveUser(user);
        log.info("Save user: [{}]", save);
        return true;
    }

    private User executeSaveUser(User user) {
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User save = userRepo.save(user);
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        inMemoryUserDetailsManager.createUser(userDetails);
        return save;
    }
}
