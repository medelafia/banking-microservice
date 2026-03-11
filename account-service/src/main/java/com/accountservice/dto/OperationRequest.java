package com.accountservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OperationRequest {
    private BigDecimal amount;
    private String destAccountId;
}
