package com.example.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    int userID;
    int roleID;
    int customerID;
    String username;
    int isActive;
    LocalDate dateOfBirth;
    String phonenumber;
    int Amount;
    String address;
    LocalDate cardDate;
    String cardPlace;

}
