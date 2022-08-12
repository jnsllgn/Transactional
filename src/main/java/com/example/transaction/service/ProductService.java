package com.example.transaction.service;

import com.example.transaction.entity.Product;
import com.example.transaction.repo.ProductRepo;
import com.example.transaction.utility.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public List<Product> save(MultipartFile file) {
        try{
            List<Product> products = CSVHelper.csvToProduts(file.getInputStream());
            return productRepo.saveAll(products);
        }catch(Exception e){
            throw new RuntimeException("Gagal menambahkan data csv" + e.getMessage());
        }

    }

    public List<Product> fidAll(){
        return productRepo.findAll();
    }

}
