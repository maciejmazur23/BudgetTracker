package com.example.budgettracker.service;

import com.example.budgettracker.entities.UserEntity;

public interface UserService {

    Long getIdByEmail(String email);

    boolean saveUser(UserEntity userEntity);
}
