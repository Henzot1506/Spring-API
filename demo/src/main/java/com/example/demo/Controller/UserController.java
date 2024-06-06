package com.example.demo.Controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.request.UpdatePassword;
import com.example.demo.model.request.User;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    @GetMapping("/get-all-user")
    public ResponseEntity<ResponseData> getAllUser(@RequestParam String authToken,@RequestParam int userID){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else if (!JwtUtil.isAdminToken(authToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseData(4,"Forbidden !"));
        }
        return userService.getAllUser(userID);
    }
    @PostMapping("/create-user")
    public  ResponseEntity<ResponseData> createUser(@RequestBody User createUser){
        return userService.createUser(createUser);
    }
    @PostMapping("/update-password")
    public ResponseEntity<ResponseData> update_password(@RequestBody UpdatePassword updatePassword,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else  return  userService.update_password(updatePassword);
    }
}
