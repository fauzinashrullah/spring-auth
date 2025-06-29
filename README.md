# 🔐 Spring Boot Authentication Service

![Java](https://img.shields.io/badge/Java-24-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.5-green)
![License](https://img.shields.io/badge/license-MIT-lightgrey)
![Status](https://img.shields.io/badge/status-Active-success)

A simple authentication and role-based authorization system built with Spring Boot, JWT, and Spring Security.

---

## 🚀 Features

- User registration & login
- Password encryption with BCrypt
- JWT token generation & validation
- Role-based access control (`USER`, `ADMIN`)
- Global exception handling
- Input validation
- Secured REST endpoints with `@PreAuthorize`

---

## 🧱 Tech Stack

- Java 24
- Spring Boot 3.5
- Spring Security
- JWT (io.jsonwebtoken / jjwt)
- Hibernate + JPA
- PostgreSQL
- Maven

---

## 📦 Endpoints

| Method | Endpoint              | Description               | Role Required    |
|--------|------------------------|---------------------------|------------------|
| POST   | `/api/auth/register`   | Register new user         | ❌ Public        |
| POST   | `/api/auth/login`      | Login and get JWT         | ❌ Public        |
| GET    | `/api/user/profile`    | Get current user profile  | ✅ USER / ADMIN  |
| GET    | `/api/admin/users`     | List all users            | ✅ ADMIN         |

---

## 📄 Sample `LoginRequest`

```json
{
  "email": "user@example.com",
  "password": "your_password"
}
```
---

## 📁 Project Structure
```
src/
├── config/
├── auth/
│   ├── controller/
│   ├── dto/
│   ├── service/
│   └── jwt/
├── model/
│   ├── User.java
│   └── Role.java
├── exception/
└── repository/
```
---

## ✅ Best Practices Implemented

- Separation of concerns
- Secure password handling
- Role-based method security
- Proper exception structure
- Validation with meaningful errors

---

## ✨ Future Improvements

- Refresh token mechanism
- Email verification
- OAuth2 login (Google/GitHub)
- Integration tests

---

## 🧑‍💻 Author

Fauzi Malik Nashrullah  
Backend Developer | Java & Spring Boot Enthusiast  
GitHub: [@fauzinashrullah](https://github.com/fauzinashrullah)  
LinkedIn: [linkedin.com/in/fauzi-malik-nashrullah](https://www.linkedin.com/in/fauzi-malik-nashrullah)

