package com.accountservice.services;

import com.accountservice.dto.AccountResponse;
import com.accountservice.dto.OperationRequest;
import com.accountservice.dto.OperationResponse;
import com.accountservice.entities.Account;
import com.accountservice.enums.TransactionStatus;
import com.accountservice.enums.TransactionType;
import com.accountservice.events.TransactionCreatedEvent;
import com.accountservice.exceptions.AccountNotFoundException;
import com.accountservice.exceptions.TransactionException;
import com.accountservice.repositories.AccountRepository;
import com.accountservice.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServices {
    private final AccountRepository accountRepository;
    private final KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate;
    private static final String TOPIC = "transactions-topic";
    private final UserRepository  userRepository;

    public AccountServices(AccountRepository accountRepository , KafkaTemplate<String, TransactionCreatedEvent> kafkaTemplate , UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.userRepository = userRepository;
    }

    public List<AccountResponse> getAccounts() {
        List<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> account.setUser(this.userRepository.findById(account.getUserId()).getBody()));

        return accounts.stream().map(AccountResponse::from).collect(Collectors.toList()) ;
    }
    public AccountResponse getAccountById(String accountId) {
        Account account = this.accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account with id " + accountId + " doesn't exist")) ;
        account.setUser(this.userRepository.findById(account.getUserId()).getBody());
        return AccountResponse.from(account);
    }

    @Transactional
    public AccountResponse createAccount(Account account) {
        System.out.println(account.getUser().getId());
        if(this.userRepository.findById(account.getUser().getId()).getStatusCode().isError()) {
            throw new AccountNotFoundException("User with id " + account.getUser().getId() + " doesn't exist");
        }
        account.setUserId(account.getUser().getId());
        account.setAccountId(UUID.randomUUID().toString());
        account.setCreatedAt(Date.valueOf(LocalDate.now()));

        Account savedAccount = this.accountRepository.save(account);
        savedAccount.setUser(this.userRepository.findById(account.getUser().getId()).getBody());


        return AccountResponse.from(savedAccount);
    }

    @Transactional
    public void deleteAccount(String accountId) {
        if(!this.accountRepository.existsById(accountId)) {
            throw new AccountNotFoundException("Account with id " + accountId + " does not exist");
        }
        accountRepository.deleteById(accountId);
    }

    @Transactional
    public OperationResponse withdraw(String accountId ,OperationRequest operationRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account " + accountId + " not found"));
       if(operationRequest.getAmount().compareTo(account.getBalance()) > 0) {
            throw new TransactionException("Amount not enough");
        }

        account.setBalance(account.getBalance().subtract(operationRequest.getAmount()));
        this.kafkaTemplate.send(TOPIC , TransactionCreatedEvent.builder()
                .accountId(accountId)
                .amount(operationRequest.getAmount().doubleValue())
                .transactionDate(Date.valueOf(LocalDate.now()))
                .transactionTime(Time.valueOf(LocalTime.now()))
                .transactionType(TransactionType.WITHDRAW)
                .build()) ;
        accountRepository.save(account) ;

        return OperationResponse.builder()
                .status(TransactionStatus.SUCCESS)
                .TransactionType(TransactionType.WITHDRAW)
                .build() ;
    }

    @Transactional
    public OperationResponse deposit(String accountId ,OperationRequest operationRequest ) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account " + accountId + " not found"));

        if (operationRequest.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new TransactionException("Cannot deposit negative amount");
        }
        account.setBalance(account.getBalance().add(operationRequest.getAmount()));

        this.kafkaTemplate.send(TOPIC , TransactionCreatedEvent.builder()
                        .accountId(accountId)
                        .amount(operationRequest.getAmount().doubleValue())
                        .transactionDate(Date.valueOf(LocalDate.now()))
                        .transactionTime(Time.valueOf(LocalTime.now()))
                        .transactionType(TransactionType.DEPOSIT)
                .build()) ;
        accountRepository.save(account) ;

        return OperationResponse.builder()
                .status(TransactionStatus.SUCCESS)
                .TransactionType(TransactionType.DEPOSIT)
                .build();
    }

    @Transactional
    public OperationResponse transfer(String accountId , OperationRequest operationRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException("Account " + accountId + " not found"));
        Account destAccount = accountRepository.findById(operationRequest.getDestAccountId()).orElseThrow(() -> new AccountNotFoundException("Account " + operationRequest.getDestAccountId() + " not found"));

        if(operationRequest.getAmount().compareTo(account.getBalance()) > 0) {
            throw new TransactionException("Amount not enough");
        }
        account.setBalance(account.getBalance().subtract(operationRequest.getAmount()));
        destAccount.setBalance(destAccount.getBalance().add(operationRequest.getAmount()));

        this.accountRepository.save(account);
        this.accountRepository.save(destAccount);

        return OperationResponse.builder()
                .status(TransactionStatus.SUCCESS)
                .TransactionType(TransactionType.TRANSFER)
                .build();
    }

    public List<AccountResponse> getAllAccountsByUserId(String userId) {
        return this.accountRepository.findAllByUserId(userId).stream().map(AccountResponse::from).collect(Collectors.toList());
    }
}
