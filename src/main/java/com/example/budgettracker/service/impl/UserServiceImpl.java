package com.example.budgettracker.service.impl;

import com.example.budgettracker.database.entities.UserEntity;
import com.example.budgettracker.database.repositories.UserRepo;
import com.example.budgettracker.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final InMemoryUserDetailsManager inMemoryUserDetailsManager;

    private static final String REGEX_PATTERN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Override
    public Long getIdByEmail(String email) {
        log.debug("Email: [{}]", email);
        UserEntity byEmail = userRepo.findByEmail(email).orElseThrow(() -> {
            log.error("User with email [{}] not exist!", email);
            throw new RuntimeException("User not found!");
        });
        log.debug("Id: [{}]", byEmail);

        return byEmail.getId();
    }

    @Transactional
    @Override
    public boolean saveUser(UserEntity userEntity) {
        boolean matches = Pattern.compile(REGEX_PATTERN).matcher(userEntity.getEmail()).matches();

        if (!matches) {
            log.warn("Email [{}] is out of pattern!", userEntity.getEmail());
            return false;
        }

        if (userRepo.findByEmail(userEntity.getEmail()).isPresent()) {
            log.warn("User with email [{}] already exist!", userEntity.getEmail());
            return false;
        }

        UserEntity save = executeSaveUser(userEntity);
        log.info("Save user: [{}]", save);
        return true;
    }

    private UserEntity executeSaveUser(UserEntity userEntity) {
        userEntity.setRole("USER");
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity save = userRepo.save(userEntity);
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(userEntity.getEmail())
                .password(userEntity.getPassword())
                .roles(userEntity.getRole())
                .build();
        inMemoryUserDetailsManager.createUser(userDetails);
        return save;
    }
}
