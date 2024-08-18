package com.mateocuevas.challenge.service.auth;


import com.mateocuevas.challenge.dto.AuthResponse;
import com.mateocuevas.challenge.dto.LogInRequest;
import com.mateocuevas.challenge.dto.SignUpRequest;

public interface AuthService {

    AuthResponse logIn (LogInRequest logInRequest);
    AuthResponse signUp (SignUpRequest signUpRequest);

}
