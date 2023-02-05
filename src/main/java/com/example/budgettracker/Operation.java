package com.example.budgettracker;

import jakarta.persistence.*;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "purchases")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    private OPERATION operation;

    @NonNull
    private CATEGORY category;

    @NonNull
    private BigDecimal price;

    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    private User user;

    enum OPERATION {
        INCOME, COST;
    }

    enum CATEGORY {
        SPORT, ROZRYWKA, INWESTYCJE, TRANSPORT, JEDZENIE, ZAKUPY, MIESZKANIE, PREZENTY, PRZYCHODY, INNE;
    }

}
