package com.example.auth.auth.dto;

import lombok.*;
import java.util.Set;

import com.example.auth.model.Role;

import jakarta.validation.constraints.*;

@Getter
@AllArgsConstructor
public class RegisterRequest {
    @Size(min = 2, max = 50)
    @NotBlank
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank
    private String email;

    @Size(min = 8, max = 30)
    @NotBlank
    private String password;


    private Set<Role> role;
}
