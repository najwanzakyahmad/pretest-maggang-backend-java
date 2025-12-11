package com.tokoadmin.ecommerce.user.controller;

import com.tokoadmin.ecommerce.common.ApiResponse; 
import com.tokoadmin.ecommerce.security.JwtUtils;
import com.tokoadmin.ecommerce.user.dto.LoginRequest;
import com.tokoadmin.ecommerce.user.entity.User;
import com.tokoadmin.ecommerce.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(400, "Email sudah terdaftar!", null));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("CUSTOMER");
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse<>(200, "Registrasi Berhasil", savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtils.generateToken(request.getEmail());
        Map<String, String> data = new HashMap<>();
        data.put("token", token);

        return ResponseEntity.ok(new ApiResponse<>(200, "Login Berhasil", data));
    }
}