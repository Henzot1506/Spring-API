package com.example.demo.Controller;

import com.example.demo.jwt.JwtUtil;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.model.AdminModel;
import com.example.demo.model.DepositModel;
import com.example.demo.model.StatusModel;
import com.example.demo.model.response.ResponseData;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminControler {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminService adminService;
    @PostMapping("/depositApproval")
    public ResponseEntity<ResponseData> depositApproval(@RequestBody DepositModel depositModel,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else if (!JwtUtil.isAdminToken(authToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseData(4,"Forbidden !"));
        }
        return adminService.depositApproval(depositModel);
    }
    @PostMapping("/update_status_user")
    public ResponseEntity<ResponseData> updatestatususer(@RequestBody StatusModel statusModel,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else if (!JwtUtil.isAdminToken(authToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseData(4,"Forbidden !"));
        }
        return adminService.active_user(statusModel);
    }
    @DeleteMapping ("/delete_user")
    public ResponseEntity<ResponseData> deleteUser(@RequestParam int userID,@RequestParam String authToken){
        if (!JwtUtil.isTokenValidInList(authToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ResponseData(3,"Unauthorized !"));
        }
        else if (!JwtUtil.isAdminToken(authToken)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ResponseData(4,"Forbidden !"));
        }
        return adminService.deleteUser(userID);
    }
}
