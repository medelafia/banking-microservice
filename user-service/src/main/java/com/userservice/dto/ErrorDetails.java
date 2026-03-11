package com.userservice.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ErrorDetails {
    private String message;
    private Timestamp timestamp;
    private HttpStatus status;
}