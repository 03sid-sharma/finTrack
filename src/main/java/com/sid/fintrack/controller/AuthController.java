package com.sid.fintrack.controller;

import com.sid.fintrack.dto.JWTResponse;
import com.sid.fintrack.dto.LoginRequest;
import com.sid.fintrack.dto.SignUpRequest;
import com.sid.fintrack.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid SignUpRequest  signUpRequest) {
        return ResponseEntity.ok(userService.register(signUpRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JWTResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));

    }
}
