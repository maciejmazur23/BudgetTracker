package com.example.budgettracker.entities;

import com.example.budgettracker.model.enums.CATEGORY;
import com.example.budgettracker.model.enums.OPERATION;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Setter
@Getter
@Entity(name = "operations")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @NonNull
    @Column(name = "date")
    private LocalDate date;

    @NonNull
    @Enumerated(value = EnumType.ORDINAL)
    @Column(name="operation")
    private OPERATION operation;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "category")
    private CATEGORY category;

    @NonNull
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "user_id")
    private Long userId;
}
