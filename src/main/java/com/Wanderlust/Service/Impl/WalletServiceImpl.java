package com.Wanderlust.Service.Impl;

import com.Wanderlust.Exception.UserException;
import com.Wanderlust.Exception.WalletException;
import com.Wanderlust.Model.Expense;
import com.Wanderlust.Model.ExpenseType;
import com.Wanderlust.Model.User;
import com.Wanderlust.Model.Wallet;
import com.Wanderlust.Repository.UserRepository;
import com.Wanderlust.Repository.WalletRepository;
import com.Wanderlust.Service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }


    /**
     * Adds money to the user's wallet and logs it as an expense.
     *
     * @param email  The email of the user.
     * @param amount The amount to add to the wallet.
     * @return The updated wallet after adding money.
     * @throws UserException If the user is not found.
     */
    @Override
    @Transactional
    public Wallet addMoney(String email, Double amount) {
        Optional<User> u = userRepository.findByEmail(email);
        u.orElseThrow(() -> new UserException("User not found"));
        User user = u.get();
        Wallet wallet = user.getWallet();

        if (wallet == null) {
            throw new WalletException("Wallet not found");
        }

        if (amount < 0) {
            throw new UserException("Amount cannot be negative");
        }


        wallet.setBalance(wallet.getBalance() + amount);

        Expense expense = new Expense();
        expense.setAmount(amount);
        expense.setExpenseDate(LocalDateTime.now());
        expense.setExpenseType(ExpenseType.Credit);
        expense.setCurrentBalance(wallet.getBalance());
        expense.setCategory("Wallet Recharge");

        wallet.getExpenses().add(expense);

        return walletRepository.save(wallet);


    }


    @Override
    public List<Expense> getAllExpense(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));

        Wallet wallet = user.getWallet();
        List<Expense> expenses = wallet.getExpenses();
        if (expenses.isEmpty()) {
            throw new UserException("No expenses found");
        }
        return expenses;

    }

    @Override
    @Transactional
    public Wallet payRideBill(String email, Double bill) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));
        Wallet wallet = user.getWallet();
        wallet.setBalance(wallet.getBalance() - bill);

        if (wallet.getBalance() < 0) {
            throw new WalletException("Insufficient balance");
        }

        Expense expense = new Expense();
        expense.setAmount(bill);
        expense.setExpenseDate(LocalDateTime.now());
        expense.setExpenseType(ExpenseType.Debit);
        expense.setCurrentBalance(wallet.getBalance());
        expense.setCategory("Ride Bill");

        wallet.getExpenses().add(expense);

        return walletRepository.save(wallet);
    }

    @Override
    public Wallet getWallet(String email) {
        User user = userRepository.findByEmail(email).get();
        return user.getWallet();
    }
}
