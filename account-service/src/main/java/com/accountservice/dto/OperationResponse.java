package com.accountservice.dto;


import com.accountservice.enums.TransactionStatus;
import com.accountservice.enums.TransactionType;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OperationResponse {
    private TransactionType TransactionType;
    private TransactionStatus status ;
}
