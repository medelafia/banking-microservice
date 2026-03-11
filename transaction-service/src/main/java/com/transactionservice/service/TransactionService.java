package com.transactionservice.service;

import com.transactionservice.entities.NotificationEvent;
import com.transactionservice.entities.Transaction;
import com.transactionservice.events.TransactionCreatedEvent;
import com.transactionservice.repositories.TransactionRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction save(Transaction transaction) {

        return transactionRepository.save(transaction);
    }
    public List<Transaction> getAccountTransactions(String accountId) {
        return this.transactionRepository.findAllByAccountId(accountId);
    }
    public List<Transaction> getTodayTransactions() {
        return this.transactionRepository.findAllByDate(Date.valueOf(LocalDate.now()));
    }

    @KafkaListener(topics = "transactions-topic" , groupId = "transaction-group")
    public void listenTransaction(TransactionCreatedEvent transactionCreatedEvent) {
        Transaction transaction = Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .date(transactionCreatedEvent.getTransactionDate())
                .amount(BigDecimal.valueOf(transactionCreatedEvent.getAmount()))
                .transactionType(transactionCreatedEvent.getTransactionType())
                .time(transactionCreatedEvent.getTransactionTime())
                .accountId(transactionCreatedEvent.getAccountId())
                .build();

        this.transactionRepository.save(transaction);
    }
}
