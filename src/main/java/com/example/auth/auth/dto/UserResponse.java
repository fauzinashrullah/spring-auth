package com.example.auth.auth.dto;

import lombok.*;
import java.util.Set;

import com.example.auth.model.Role;

@AllArgsConstructor
@Getter
public class UserResponse {
    private String name;
    private String email;
    private Set<Role> role;
}
