package com.mateocuevas.challenge.service.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.function.Function;

public interface JwtService {
    String getToken(UserDetails user);
    String getUsernameFromToken(String token);
    String getRoleFromUser(UserDetails user);
    boolean isTokenValid(String token, UserDetails userDetails);

    <T> T getClaim(String token, Function<Claims,T> claimsResolver);
}
