package com.example.ocm.controller;



import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.example.ocm.dto.LoginRequest;
import com.example.ocm.entity.User;
import com.example.ocm.service.AuthService;

@RestController
@RequestMapping("/api/auth")

@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService service;
    
    public AuthController( AuthService service) {
    	this.service = service;
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return service.register(user);
    }
//
//    @PostMapping("/login")
//    public String login(@RequestBody LoginRequest req) {
//        return service.login(req.getEmail(), req.getPassword());
//    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginRequest req) {
        return service.login(req.getEmail(), req.getPassword());
    }

}

