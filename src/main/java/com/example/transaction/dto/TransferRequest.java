package com.example.transaction.dto;

import lombok.Data;

@Data
public class TransferRequest {
    private String noRekAsal;
    private String noRekTujuan;
    private double jumlahTransfer;


}
