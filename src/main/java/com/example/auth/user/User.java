package com.example.auth.user;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){};

    public User(String name, String email, String password, Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }public String getName() {
        return name;
    }public String getEmail() {
        return email;
    }public String getPassword() {
        return password;
    }public Role getRole() {
        return role;
    }
}
