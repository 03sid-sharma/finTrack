package com.sid.fintrack.service;

import com.sid.fintrack.dto.JWTResponse;
import com.sid.fintrack.dto.LoginRequest;
import com.sid.fintrack.dto.SignUpRequest;
import com.sid.fintrack.entity.User;
import com.sid.fintrack.repository.UserRepository;
import com.sid.fintrack.security.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JWTService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(SignUpRequest  signUpRequest) {
        if(userRepository.findByEmail(signUpRequest.email()).isPresent()){
            throw new RuntimeException();
        }

        User user = new User();
        user.setEmail(signUpRequest.email());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setRole("USER");

        userRepository.save(user);
        return "User Registered Successfully!";
    }

    public JWTResponse login(LoginRequest loginRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                )
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtService.generateToken(user);
        return new JWTResponse(token, "Login Successful!");
    }
}
