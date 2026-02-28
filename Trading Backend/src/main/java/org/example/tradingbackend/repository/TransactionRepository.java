package org.example.tradingbackend.repository;

import org.example.tradingbackend.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUsernameOrderByIdDesc(String username);
}
