package com.accountservice.exceptions;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
public class AccountNotFoundException extends RuntimeException {
    private String message ;
}
