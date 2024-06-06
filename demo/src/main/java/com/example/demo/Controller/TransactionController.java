package com.example.demo.Controller;


import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.TransactionMapper;
import com.example.demo.model.TransactionModel;
import com.example.demo.model.request.DepositMoney;
import com.example.demo.model.request.GetTransactionID;
import com.example.demo.model.request.SellPayment;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionService transactionService;
    @PostMapping("/depositmoney")
    public ResponseEntity<ResponseData> depositMoney(@RequestBody DepositMoney depositMoney, @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return transactionService.depositMoney(depositMoney);
    }
    @PostMapping("/buypayment")
    public ResponseEntity<ResponseData> buyPayment(@RequestBody TransactionModel transactionModel, @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return transactionService.buy_payment(transactionModel);
    }
    @PostMapping("/sellpayment")
    public ResponseEntity<ResponseData> sellPayment(@RequestBody SellPayment sellPayment, @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return transactionService.sell_payment(sellPayment);
    }
    @GetMapping("/transactiondata")
    public ResponseEntity<ResponseData> getTransaction(@RequestParam int transactionType, @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return  transactionService.getTransaction(transactionType);
    }
    @PostMapping ("/transaction_by_ID")
    public ResponseEntity<ResponseData> getTransactionByID(@RequestBody GetTransactionID getTransactionID,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return  transactionService.getTransactionByID(getTransactionID);
    }
    @GetMapping("/depositNotApproval")
    public ResponseEntity<ResponseData> depositNotApproval(@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else if (!JwtUtil.isAdminToken(authToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseData(4,"Forbidden !"));
        }
        return  transactionService.depositNotAprroval();
    }
    @GetMapping("/get_all_stock")
    public ResponseEntity<ResponseData> getAllStock(){
        return  transactionService.getAllStock();
    }
    @GetMapping("get_amount_by_ID")
    public ResponseEntity<ResponseData> getAmountbyID(@RequestParam int customerID,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else  return transactionService.getAmountbyID(customerID);
    }
    @GetMapping("get_balance_by_ID")
    public ResponseEntity<ResponseData> getBalance(@RequestParam int customerID,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else  return transactionService.getBallance(customerID);
    }
}
