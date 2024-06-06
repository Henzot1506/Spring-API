package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.model.request.UpdatePassword;
import com.example.demo.model.request.User;
import com.example.demo.model.response.ResponseData;
import com.example.demo.model.response.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public ResponseEntity<ResponseData> getAllUser(int userID){
        List<UserInfo> list = userMapper.getAllUser(userID);
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> createUser(User User){
        if (userMapper.createUser(User) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Create User Success",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"User is exits! ",""));
    }
    public ResponseEntity<ResponseData> update_password(UpdatePassword updatePassword){
        if (userMapper.update_password(updatePassword) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Update Password Success",""));
        else if (userMapper.update_password(updatePassword) == 2) {
            return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(2,"Wrong old Password ! ",""));
        } else return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong ID User ! ",""));
    }

}
