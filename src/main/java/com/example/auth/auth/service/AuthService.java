package com.example.auth.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth.auth.dto.AuthResponse;
import com.example.auth.auth.dto.LoginRequest;
import com.example.auth.auth.dto.RegisterRequest;
import com.example.auth.auth.jwt.JwtService;
import com.example.auth.exception.EmailAlreadyUsedException;
import com.example.auth.repository.UserRepository;
import com.example.auth.user.User;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;

    }

    public AuthResponse register(RegisterRequest request){
        if (repository.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyUsedException("Email already used");
        }else{
            User user = new User(
                request.getName(), request.getEmail(), 
                passwordEncoder.encode(request.getPassword()), 
                request.getRole());
    
            repository.save(user);
            return new AuthResponse(jwtService.generateToken(user.getEmail()));
        }
    }

    public AuthResponse login(LoginRequest request){
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow((() -> new RuntimeException("User not found")));
        
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }else{
            return new AuthResponse(jwtService.generateToken(user.getEmail()));
        }
    }
}
