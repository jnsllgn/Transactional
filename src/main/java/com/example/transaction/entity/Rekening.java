package com.example.transaction.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="tbl_rekening")
@Data
public class Rekening {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String norek;
    private String name;
    private double saldo;

    public Rekening(String norek, String name, double saldo) {
        this.norek = norek;
        this.name = name;
        this.saldo = saldo;
    }
    public Rekening(){

    }
}
