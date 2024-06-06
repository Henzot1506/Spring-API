package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtil {
    private static final String jwtSecret = "2ayjNYX4pYS/fdJ2jNnD9UbrAoSEYdoikaxt07l/BRY=";
    private static final long jwtExpirationInMs = 1800000;

    // List to store tokens
    private static List<String> tokenList = new ArrayList<>();

    public static String refreshExpiredToken(String expiredToken) {
        if (validateToken(expiredToken)) {
            try {
                tokenList.remove(expiredToken);
                String newToken = generateToken(extractUsername(expiredToken),extractRole(expiredToken));
                tokenList.add(newToken);
                return newToken;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String generateToken(String username, String role) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        String token = Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        // Add the generated token to the token list
        tokenList.add(token);
        return token;
    }

    public static String extractRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class); // Trích xuất giá trị của trường "role"
    }

    public static boolean isAdminToken(String token) {
        String role = extractRole(token);
        return role != null && role.equals("admin");
    }



    public static String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public static Date extractExpireDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

    public static boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
    public static boolean isTokenValidInList(String token) {
        return tokenList.contains(token) && validateToken(token);
    }

}
