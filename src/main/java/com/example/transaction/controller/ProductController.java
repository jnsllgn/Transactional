package com.example.transaction.controller;

import com.example.transaction.dto.ResponseData;
import com.example.transaction.entity.Product;
import com.example.transaction.service.ProductService;
import com.example.transaction.utility.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        ResponseData responseData = new ResponseData();
        try{
            List<Product> products = productService.fidAll();
            responseData.setStatus(true);
            responseData.getMessages().add("Mendapatkan semua buku");
            responseData.setPayload(products);
            return ResponseEntity.ok(responseData);
        }catch(Exception e){
            responseData.setStatus(false);
            responseData.getMessages().add("Tidak dapat menemukan buku " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
        }
    }
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        ResponseData responseData = new ResponseData();
        if(!CSVHelper.hasCSVFormat(file)){
            responseData.setStatus(false);
            responseData.getMessages().add("Silahkan upload File ");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        try{
            List<Product> products = productService.save(file);
            responseData.setStatus(true);
            responseData.getMessages().add("Unggah file sukses " + file.getOriginalFilename());
            responseData.setPayload(products);
            return ResponseEntity.ok(responseData);
        }catch(Exception e){
            responseData.setStatus(false);
            responseData.getMessages().add("Tidak bisa mengunggah file " + file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(responseData);
        }
    }

}
