package com.example.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<String> handleEmailUsed(EmailAlreadyUsedException ex){
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("error: " + ex.getMessage());
    }

}
