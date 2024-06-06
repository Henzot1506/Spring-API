package com.example.demo.service;

import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.CustomerModel;
import com.example.demo.model.InfoModal;
import com.example.demo.model.request.Forgot;
import com.example.demo.model.request.ForgotUpdate;
import com.example.demo.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    @Autowired
    CustomerMapper customerMapper;

    public ResponseEntity<ResponseData> createCustomer(CustomerModel customerModel){
        if (customerMapper.createCustomer(customerModel) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Create Customer Success",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Customer is exits! ",""));
    }
    public ResponseEntity<ResponseData> getAllCustomer(){
        List<CustomerModel> list = customerMapper.getAllcustomer();
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> deleteCustomer(int CustomerID){
        if (customerMapper.deleteCustomer(CustomerID) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Delete Customer Success",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Undefinded Customer ",""));
    }
    public ResponseEntity<ResponseData> getCustomerbyID(int userID){
        List<CustomerModel> list = customerMapper.getCustomerbyID(userID);
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> update_info(InfoModal infoModal){

        if (customerMapper.update_info(infoModal) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Update Information Success",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong Customer ID ",""));
    }
    public ResponseEntity<ResponseData> check_forgot_password(Forgot forgot){
        if (customerMapper.check_forgot_password(forgot) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"True",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong Info ",""));
    }
    public ResponseEntity<ResponseData> update_forgot_password(ForgotUpdate forgotUpdate){
        if (customerMapper.forgot_password_update(forgotUpdate) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Update Success",""));
        else if (customerMapper.forgot_password_update(forgotUpdate) == 2)
            return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(2,"Duplicate Password ",""));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"Wrong Info Data ",""));
    }
}
