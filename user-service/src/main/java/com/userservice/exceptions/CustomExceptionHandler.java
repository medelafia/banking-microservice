package com.userservice.exceptions;


import com.userservice.dto.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.Instant;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.ok(
                ErrorDetails.builder()
                        .status(HttpStatus.NOT_FOUND)
                        .message(e.getMessage())
                        .timestamp(Timestamp.from(Instant.now()))
                        .build()
        ) ;
    }
}
