package com.example.transaction.service;

import com.example.transaction.entity.Rekening;
import com.example.transaction.repo.RekeningRepo;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jboss.logging.Logger;
import springfox.documentation.annotations.Cacheable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RekeningService {
    Logger logger = new LoggerFactory().logger(this.getClass());

    @Autowired
    private RekeningRepo rekeningRepo;
    public Rekening create(Rekening rekening){
        return rekeningRepo.save(rekening);
    }
        public List<Rekening>findAll(){
//            logger.info("Data diambil dari database");
            return rekeningRepo.findAll();
        }

        @Cacheable(value = "rekening")
        public Rekening getRekeningId(long id){
//            logger.info("Data diambil dari database");
            Optional<Rekening> rekening = rekeningRepo.findById(id);
            if(rekening.isPresent()){
                return rekening.get();
            }
            else {
                return new Rekening();
            }
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
