package com.example.auth.auth.dto;

import lombok.*;

@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String error;
    private String message;
}
