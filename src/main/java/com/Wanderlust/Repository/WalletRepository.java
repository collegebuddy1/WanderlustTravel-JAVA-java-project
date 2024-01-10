package com.Wanderlust.Repository;

import com.Wanderlust.Model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
