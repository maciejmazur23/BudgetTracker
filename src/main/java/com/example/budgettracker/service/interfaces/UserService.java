package com.example.budgettracker.service.interfaces;

import com.example.budgettracker.database.entities.UserEntity;

public interface UserService {

    Long getIdByEmail(String email);

    boolean saveUser(UserEntity userEntity);
}
