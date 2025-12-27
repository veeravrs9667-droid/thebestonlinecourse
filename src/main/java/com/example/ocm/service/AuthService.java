package com.example.ocm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ocm.entity.User;
import com.example.ocm.repository.UserRepository;
import com.example.ocm.security.JwtUtil;

@Service
public class AuthService {

    private final UserRepository repo;
    private final JwtUtil jwt;
    private final PasswordEncoder encoder;   // <-- Injected bean

    public AuthService(UserRepository repo, JwtUtil jwt, PasswordEncoder encoder) {
        this.repo = repo;
        this.jwt = jwt;
        this.encoder = encoder;
    }

    // ------------------- REGISTER -------------------
    public String register(User u) {

        // Encode password before saving
        u.setPassword(encoder.encode(u.getPassword()));

        // Set default role
        if (u.getRole() == null || u.getRole().isEmpty()) {
            u.setRole("student"); 
            
        }

        repo.save(u);
        return "Registered Successfully";
    }


    // ------------------- LOGIN -------------------
    public Map<String, Object> login(String email, String rawPassword) {

        User u = repo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate password
        if (!encoder.matches(rawPassword, u.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        // Generate JWT
        String token = jwt.generateToken(email);

        Map<String, Object> res = new HashMap<>();
        res.put("token", token);
        res.put("role", u.getRole());
        res.put("id", u.getId());

        return res;
    }
}
