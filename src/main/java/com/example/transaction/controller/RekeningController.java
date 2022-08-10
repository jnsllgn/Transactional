package com.example.transaction.controller;

import com.example.transaction.dto.TransferRequest;
import com.example.transaction.entity.Rekening;
import com.example.transaction.service.RekeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rekening")
public class RekeningController {

    @Autowired
    private RekeningService service;

    @PostMapping
    public Rekening create(@RequestBody Rekening rekening) {
        return service.create(rekening);
    }

    @GetMapping
    public List<Rekening> findAll() {
        return service.findAll();
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest transfer){
        service.transfer(transfer.getNoRekAsal(), transfer.getNoRekTujuan(), transfer.getJumlahTransfer());
    }

}
