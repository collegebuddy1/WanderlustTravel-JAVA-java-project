package com.Wanderlust.Service;

import com.Wanderlust.Model.Expense;
import com.Wanderlust.Model.Wallet;

import java.util.List;

public interface WalletService {

    public Wallet addMoney(String email, Double amount);

    public List<Expense> getAllExpense(String email);

    public Wallet payRideBill(String email, Double bill);

    public Wallet getWallet(String email);
}
