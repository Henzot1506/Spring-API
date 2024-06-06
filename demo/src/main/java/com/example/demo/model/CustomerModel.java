package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerModel {
    int customerID;
    int userID;
    String password;
    String name;
    LocalDate dateOfBirth;
    String address;
    String phonenumber;
    LocalDate cardDate;
    String cardPlace;
}
