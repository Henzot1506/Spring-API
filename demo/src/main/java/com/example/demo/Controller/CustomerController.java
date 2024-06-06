package com.example.demo.Controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.CustomerMapper;
import com.example.demo.model.CustomerModel;
import com.example.demo.model.InfoModal;
import com.example.demo.model.request.Forgot;
import com.example.demo.model.request.ForgotUpdate;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.AuthService;
import com.example.demo.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AuthService authenticationService;
    @PostMapping("/create-customer")
    public ResponseEntity<ResponseData> createCustomer(@RequestBody CustomerModel createCustomer){
        return customerService.createCustomer(createCustomer);
    }
    @GetMapping("/get-all-customer")
    public  ResponseEntity<ResponseData> getAllCustomer( @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return  customerService.getAllCustomer();
    }
    @DeleteMapping("/delete-customer")
    public  ResponseEntity<ResponseData> deleteCustomer(@RequestParam int CustomerID, @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return  customerService.deleteCustomer(CustomerID);

    }
    @GetMapping("get_customer_by_ID")
    public ResponseEntity<ResponseData> getCustomerbyID(@RequestParam int userID, @RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return customerService.getCustomerbyID(userID);

    }
    @PostMapping("/update_info")
    public ResponseEntity<ResponseData> update_info(@RequestBody InfoModal infoModal, @RequestParam String authToken) {
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized"));
        }
        return customerService.update_info(infoModal);
    }
    @PostMapping("/check_forgot_password")
    public ResponseEntity<ResponseData> check_forgot_password(@RequestBody Forgot forgot){
        return customerService.check_forgot_password(forgot);
    }
    @PostMapping("/update_forgot_password")
    public ResponseEntity<ResponseData> forgot_password_update(@RequestBody ForgotUpdate forgotUpdate){
        return customerService.update_forgot_password(forgotUpdate);
    }
}
