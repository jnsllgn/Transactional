package com.example.transaction.service;

import com.example.transaction.entity.Rekening;
import com.example.transaction.repo.RekeningRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RekeningService {
    @Autowired
    private RekeningRepo rekeningRepo;
    public Rekening create(Rekening rekening){
        return rekeningRepo.save(rekening);
    }
        public List<Rekening>findAll(){
            return rekeningRepo.findAll();
        }



    @Transactional
    public void transfer(String noRekAsal,String noRekTujuan,double jumlahTransfer) {
        Rekening rekeningAsal = rekeningRepo.findByNorek(noRekAsal);
        if(rekeningAsal == null){
            throw new RuntimeException("Norek pengirim tidak valid");
        }
        if(rekeningAsal.getSaldo()<jumlahTransfer){
            throw new RuntimeException("Saldo tidak cukup");
        }
        rekeningAsal.setSaldo(rekeningAsal.getSaldo()-jumlahTransfer);
        rekeningRepo.save(rekeningAsal);

        Rekening rekeningTujuan= rekeningRepo.findByNorek(noRekTujuan);
        if(rekeningTujuan == null){
            throw new RuntimeException("Norek penerima tidak valid");
        }
        rekeningTujuan.setSaldo(rekeningTujuan.getSaldo()+jumlahTransfer);
        rekeningRepo.save(rekeningTujuan);


    }

}
