package com.stackroute.MovieApp.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletException;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public Jws<Claims> parseClaimsJws(String token, String secretKey) throws ServletException {
        try {
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        } catch (Exception e) {
            throw new ServletException("Invalid token", e);
        }
    }
}