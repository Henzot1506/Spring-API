package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailModel {
    String transactionID;
    int CustomerID;
    int transactionMoney;
    String description;
    int status;
    int count;
    Date transactionDate;
    String approveBy;
    String stockname;
}
