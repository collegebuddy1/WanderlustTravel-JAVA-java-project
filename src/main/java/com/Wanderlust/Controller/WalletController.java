package com.Wanderlust.Controller;

import com.Wanderlust.Model.Expense;
import com.Wanderlust.Model.Wallet;
import com.Wanderlust.Service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/addMoney/{email}/{amount}")
    public ResponseEntity<Wallet> addMoneyToWallet(@PathVariable String email, @PathVariable Double amount) {
        Wallet updatedWallet = walletService.addMoney(email, amount);
        return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<Expense>> getAllExpenses(@PathVariable String email) {
        List<Expense> expenses = walletService.getAllExpense(email);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @PostMapping("/pay-ride-bill")
    public ResponseEntity<Wallet> payRideBill(@PathVariable String email, @PathVariable Double bill) {
        Wallet updatedWallet = walletService.payRideBill(email, bill);
        return new ResponseEntity<>(updatedWallet, HttpStatus.OK);
    }

    @GetMapping("/user-wallet")
    public ResponseEntity<Wallet> getUserWallet(@PathVariable String email) {
        Wallet wallet = walletService.getWallet(email);
        return new ResponseEntity<>(wallet, HttpStatus.OK);
    }
}
