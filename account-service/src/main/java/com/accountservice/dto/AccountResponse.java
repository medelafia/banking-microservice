package com.accountservice.dto;


import com.accountservice.entities.Account;
import com.accountservice.entities.User;
import com.accountservice.enums.AccountStatus;
import com.accountservice.enums.AccountType;
import lombok.*;
import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AccountResponse {
    private String accountId;
    private String currency;
    private AccountType accountType;
    private BigDecimal balance;
    private Date createdAt;
    private AccountStatus status;
    private User user;


    public static AccountResponse from(Account account) {
        return AccountResponse.builder()
                .accountId(account.getAccountId())
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .accountType(account.getAccountType())
                .createdAt(account.getCreatedAt())
                .user(account.getUser())
                .status(account.getStatus())
                .build() ;
    }
}
