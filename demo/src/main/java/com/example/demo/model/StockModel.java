package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockModel {
    String stockID;
    String stockname;
    LocalDateTime Time;
    int MinPrice;
    int MaxPrice;
}
