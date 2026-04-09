package com.vt.water.atm.transaction.repository;

import com.vt.water.atm.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction,Integer> {

    Optional<Transaction> findByTransactionId(String transactionId);
}
