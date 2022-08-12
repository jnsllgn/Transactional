package com.example.transaction.repo;

import com.example.transaction.entity.Rekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RekeningRepo extends JpaRepository<Rekening, Long> {

      Rekening findByNorek(String norek);



}
