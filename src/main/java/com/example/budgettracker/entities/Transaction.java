package com.example.budgettracker.entities;

import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Setter
@Getter
@Entity(name = "operations")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    private OPERATION operation;

    private CATEGORY category;

    @NonNull
    private BigDecimal price;

    private String description;

    @NonNull
    private Long userId;
}
