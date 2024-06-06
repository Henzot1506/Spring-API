package com.example.demo.service;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.AuthMapper;
import com.example.demo.model.UserModel;
import com.example.demo.model.request.User;
import com.example.demo.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.demo.jwt.JwtUtil.refreshExpiredToken;


@Service
public class AuthService {
    @Autowired
    AuthMapper authMapper;
    Date now = new Date();
    public ResponseEntity<ResponseData> loginUser(User loginUser) {
        UserModel user = authMapper.loginUser(loginUser);
        if (user != null){
            String token = JwtUtil.generateToken(loginUser.getUsername(),"user");
            Date expiryDate = new Date(now.getTime() + 900000);
            user.setToken(token);
            user.setExpirationTime(expiryDate);
            return ResponseEntity.ok().body(new ResponseData<>(0,"Login Success",user));
        }

        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong username or Password! "));
    }
    public ResponseEntity<ResponseData> loginAdmin(User loginAdmin) {
        UserModel user = authMapper.adminLogin(loginAdmin);
        if (user != null){
            String token = JwtUtil.generateToken(loginAdmin.getUsername(),"admin");
            Date expiryDate = new Date(now.getTime() + 900000);
            user.setToken(token);
            user.setExpirationTime(expiryDate);
            return ResponseEntity.ok().body(new ResponseData<>(0,"Login Success",user));
        }

        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong username or Password! "));
    }
    public ResponseEntity<ResponseData> RefreshToken(String token){
        String refreshToken =  refreshExpiredToken(token);
        if (refreshToken !=null)

            return ResponseEntity.ok().body(new ResponseData<>(0,"Refresh Success !",refreshToken));
        else return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong Token! "));
    }
}
