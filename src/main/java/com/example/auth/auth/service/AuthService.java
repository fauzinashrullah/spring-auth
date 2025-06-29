package com.example.auth.auth.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.auth.dto.*;
import com.example.auth.auth.jwt.JwtService;
import com.example.auth.exception.CustomException;
import com.example.auth.repository.UserRepository;
import com.example.auth.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;

    

    public AuthResponse register(RegisterRequest request){
        if (repository.findByEmail(request.getEmail()).isPresent()){
            throw new CustomException(HttpStatus.CONFLICT, "Conflict", "Email already in use");
        }else{
            User user = new User(
                request.getName(), request.getEmail(), 
                passwordEncoder.encode(request.getPassword()), 
                request.getRole());
    
            repository.save(user);
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            return new AuthResponse(jwtService.generateToken(userDetails));
        }
    }

    public AuthResponse login(LoginRequest request){
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow((() -> new CustomException
            (HttpStatus.NOT_FOUND, "User not found", "No user found with email: " + request.getEmail())));
        
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new CustomException(HttpStatus.UNAUTHORIZED, "Invalid login", "Password does not match");
        }else{
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            return new AuthResponse(jwtService.generateToken(userDetails));
        }
    }

    public List<UserResponse> getAllUsers(){
        return repository.findAll().stream()
            .map(user -> new UserResponse(user.getName(), user.getEmail(), user.getRole()))
            .collect(Collectors.toList());
    }

    public UserResponse getProfile(UserDetails userDetails){
        String email = userDetails.getUsername();
        User user = repository.findByEmail(email).orElseThrow(
            () -> new CustomException(HttpStatus.NOT_FOUND, "User not found", "No user found with email: " + email)
        );
        return new UserResponse(user.getName(), user.getEmail(), user.getRole());
    }
}