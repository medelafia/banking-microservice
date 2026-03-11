package com.transactionservice.repositories;

import com.transactionservice.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
    public List<Transaction> findAllByAccountId(String accountId) ;
    public List<Transaction> findAllByDate(Date date);
}
