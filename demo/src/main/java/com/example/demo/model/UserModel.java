package com.example.demo.model;

import com.example.demo.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
    int userID;
    String username;
    int isActive;
    String token;
    Date ExpirationTime;
    public String getUsername() {
        return username;
    }

    public void setExpirationTime(Date expirationTime) {
        ExpirationTime = expirationTime;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
