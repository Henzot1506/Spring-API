package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoModal {
    int customerID;
    String name;
    LocalDate dateOfBirth;
    String address;
    String phonenumber;
}
