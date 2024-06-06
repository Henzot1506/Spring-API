package com.example.demo.Controller;

import com.example.demo.mapper.AuthMapper;
import com.example.demo.model.AdminModel;
import com.example.demo.model.request.User;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private AuthService authService;
    @PostMapping("/login-user")
    public ResponseEntity<ResponseData> loginUser(@RequestBody User loginUser){
        return  authService.loginUser(loginUser);
    }
    @PostMapping("/login-admin")
    public ResponseEntity<ResponseData> adminLogin (@RequestBody User loginAdmin){
        return authService.loginAdmin(loginAdmin);
    }
    @GetMapping("/refreshToken")
    public ResponseEntity<ResponseData> refreshToken(@RequestParam String token){
        return  authService.RefreshToken(token);
    }
}
