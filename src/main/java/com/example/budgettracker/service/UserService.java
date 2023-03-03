package com.example.budgettracker.service;

import com.example.budgettracker.entities.User;

public interface UserService {

    Long getIdByEmail(String email);

    boolean saveUser(User user);
}
