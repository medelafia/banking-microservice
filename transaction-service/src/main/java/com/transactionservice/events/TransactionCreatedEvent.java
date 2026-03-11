package com.transactionservice.events;



import com.transactionservice.enums.TransactionType;
import lombok.*;

import java.sql.Date;
import java.sql.Time;


@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class TransactionCreatedEvent {
    private String accountId ;
    private TransactionType transactionType ;
    private Date transactionDate ;
    private Time transactionTime ;
    private Double amount ;
}
