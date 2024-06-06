package com.example.demo.service;

import com.example.demo.mapper.AdminMapper;
import com.example.demo.model.AdminModel;
import com.example.demo.model.DepositModel;
import com.example.demo.model.StatusModel;
import com.example.demo.model.UserModel;
import com.example.demo.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    public ResponseEntity<ResponseData> depositApproval(DepositModel depositModel){
        if (adminMapper.depositApproval(depositModel) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Deposit Success",""));
        else if (adminMapper.depositApproval(depositModel) == 2)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseData<>(2,"Cannot Approve your self !"));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong ID data ! ",""));

    }
    public ResponseEntity<ResponseData> active_user(StatusModel statusModel){

        if (adminMapper.active_user(statusModel) == 1 )
            return ResponseEntity.ok().body(new ResponseData<>(0,"Update status Success",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong ID data ! ",""));
    }
    public ResponseEntity<ResponseData> deleteUser(int userID){
        if (adminMapper.deleteUser(userID) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"User deleted",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong ID data ! ",""));
    }
}
