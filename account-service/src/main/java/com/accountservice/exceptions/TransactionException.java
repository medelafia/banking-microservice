package com.accountservice.exceptions;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TransactionException extends RuntimeException {
    private String message ;
}
