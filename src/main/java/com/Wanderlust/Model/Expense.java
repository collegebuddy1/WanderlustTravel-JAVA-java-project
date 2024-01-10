package com.Wanderlust.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer expenseID;

    private LocalDateTime expenseDate;;
    @DecimalMin("0.0")
    private Double amount;

    @NotNull(message = "Category cannot be null")
    @Size(min = 3, max = 30, message = "Category must be between 2 and 30 characters")
    private String category;

    @DecimalMin("0.0")
    private Double CurrentBalance;
    @Enumerated(EnumType.STRING)
    private ExpenseType expenseType;

}
