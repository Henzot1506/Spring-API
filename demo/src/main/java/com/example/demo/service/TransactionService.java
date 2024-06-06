package com.example.demo.service;

import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.StockModel;
import com.example.demo.model.TransactionDetailModel;
import com.example.demo.model.TransactionModel;
import com.example.demo.model.request.DepositMoney;
import com.example.demo.model.request.GetTransactionID;
import com.example.demo.model.request.SellPayment;
import com.example.demo.model.response.Balance;
import com.example.demo.model.response.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionMapper transactionMapper;
    public ResponseEntity<ResponseData> depositMoney(DepositMoney depositMoney){
        if (transactionMapper.depositMoney(depositMoney) == 0)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success"));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> buy_payment(TransactionModel transactionModel){
        if (transactionMapper.buyPayment(transactionModel) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success"));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> sell_payment(SellPayment sellPayment){
        if (transactionMapper.sellPayment(sellPayment) == 1)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success"));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public  ResponseEntity<ResponseData> getTransaction(int transactionType){
        List<TransactionDetailModel> list =  transactionMapper.getTransaction(transactionType);
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> getTransactionByID(GetTransactionID getTransactionID){
        List<TransactionDetailModel> list = transactionMapper.getTransactionByID(getTransactionID);
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> getAllStock(){
        List<StockModel> list =  transactionMapper.getAllStock();
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> depositNotAprroval(){
        List<TransactionDetailModel> list =  transactionMapper.depositNotAproval();
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> getAmountbyID(int customerID){
        int amount = transactionMapper.getAmountbyID(customerID);
        if(amount >=0)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",amount));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
    public ResponseEntity<ResponseData> getBallance(int customerID){
        List<Balance> list = transactionMapper.getBalance(customerID);
        if (list != null)
            return ResponseEntity.ok().body(new ResponseData<>(0,"Success",list));
        else return   ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData(1,"failed"));
    }
}
