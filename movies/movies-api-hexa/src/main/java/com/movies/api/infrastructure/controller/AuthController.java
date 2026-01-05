package com.movies.api.infrastructure.controller;

import com.movies.api.application.port.in.dto.LoginRequest;
import com.movies.api.application.port.out.dto.AuthResponse;
import com.movies.api.infrastructure.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        if ("ouli".equals(loginRequest.getUsername()) && "wayway".equals(loginRequest.getPassword())) {

            String token = jwtUtil.generateToken(loginRequest.getUsername());
            AuthResponse response = new AuthResponse(token, jwtUtil.getExpirationTimeInSeconds());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
