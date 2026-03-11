package com.transactionservice.entities;


import com.transactionservice.enums.TransactionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Transaction {
    @Id
    private String transactionId;
    private String accountId;
    private BigDecimal amount;
    private Date date;
    private Time time;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
}
