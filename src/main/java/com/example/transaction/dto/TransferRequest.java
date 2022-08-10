package com.example.transaction.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String norek1;
    private String norek2;
    private double amount;
}
