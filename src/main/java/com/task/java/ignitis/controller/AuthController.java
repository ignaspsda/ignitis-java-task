package com.task.java.ignitis.controller;

import com.task.java.ignitis.payload.request.LoginRequest;
import com.task.java.ignitis.payload.request.SignupRequest;
import com.task.java.ignitis.payload.response.InfoResponse;
import com.task.java.ignitis.payload.response.JwtResponse;
import com.task.java.ignitis.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-in")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/signup")
    public InfoResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.registerUser(signUpRequest);
    }
}
