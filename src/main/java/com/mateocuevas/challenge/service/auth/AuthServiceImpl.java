package com.mateocuevas.challenge.service.auth;

import com.mateocuevas.challenge.dto.AuthResponse;
import com.mateocuevas.challenge.dto.LogInRequest;
import com.mateocuevas.challenge.dto.SignUpRequest;
import com.mateocuevas.challenge.entity.User;
import com.mateocuevas.challenge.enums.UserRole;
import com.mateocuevas.challenge.exception.EmailAlreadyExistsException;
import com.mateocuevas.challenge.service.jwt.JwtService;
import com.mateocuevas.challenge.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private  UserService userService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse logIn(LogInRequest logInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(logInRequest.getEmail(), logInRequest.getPassword()));
        UserDetails user = userService.findByUsername(logInRequest.getEmail()).orElseThrow();
        String token = jwtService.getToken(user);
        String role = jwtService.getRoleFromUser(user);
        return AuthResponse.builder()
                .token(token)
                .role(role)
                .build();
    }

    @Override
    public AuthResponse signUp(SignUpRequest signUpRequest) {
        if (userService.findByUsername(signUpRequest.getEmail()).isEmpty()) {
            User user = User.builder()
                    .email(signUpRequest.getEmail())
                    .password(passwordEncoder.encode(signUpRequest.getPassword()))
                    .firstName(signUpRequest.getFirstName())
                    .lastName(signUpRequest.getLastName())
                    .role(UserRole.ROLE_CUSTOMER)
                    .build();
            userService.save(user);

            return AuthResponse.builder()
                    .token(jwtService.getToken(user))
                    .build();
        } else {
            throw new EmailAlreadyExistsException("The email address is already registered.");
        }
    }
}
