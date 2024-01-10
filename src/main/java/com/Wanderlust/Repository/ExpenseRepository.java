package com.Wanderlust.Repository;

import com.Wanderlust.Model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
}
