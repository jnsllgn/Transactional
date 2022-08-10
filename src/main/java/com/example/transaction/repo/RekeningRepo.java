package com.example.transaction.repo;

import com.example.transaction.entity.Rekening;
import org.springframework.data.repository.CrudRepository;

public interface RekeningRepo extends CrudRepository<Rekening, Long> {

      Rekening findByNorek(String norek);


}
