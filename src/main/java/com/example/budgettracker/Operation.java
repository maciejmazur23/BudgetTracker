package com.example.budgettracker;

import com.example.budgettracker.model.CATEGORY;
import com.example.budgettracker.model.OPERATION;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Setter
@Getter
@Entity(name = "operations")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private LocalDate date;

    @NonNull
    private String operation;

    @NonNull
    private String category;

    @NonNull
    private BigDecimal price;

    @NonNull
    private String description;

    @NonNull
    @ManyToOne
    private User user;

}
