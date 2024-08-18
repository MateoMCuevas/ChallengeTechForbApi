package com.mateocuevas.challenge.controller;

import com.mateocuevas.challenge.dto.AuthResponse;
import com.mateocuevas.challenge.dto.LogInRequest;
import com.mateocuevas.challenge.dto.SignUpRequest;
import com.mateocuevas.challenge.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LogInRequest logInRequest){
        return ResponseEntity.ok(authService.logIn(logInRequest));
    }
    @PostMapping(value = "/signup")
    public ResponseEntity<AuthResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
}
