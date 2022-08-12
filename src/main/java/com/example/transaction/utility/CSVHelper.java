package com.example.transaction.utility;

import com.example.transaction.entity.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    private static final String TYPE="text/csv";


    public static boolean hasCSVFormat(MultipartFile file){
        if(!TYPE.equals(file.getContentType())){
            return false;
        }
        return  true;
    }

    public static List<Product> csvToProduts(InputStream is){
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            CSVParser parser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<Product> products = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = parser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Product product = new Product();
                product.setId(Long.parseLong(csvRecord.get("Id")));
                product.setTitle(csvRecord.get("Title"));
                product.setDescription(csvRecord.get("Description"));
                product.setPrice(Double.parseDouble(csvRecord.get("Price")));
                products.add(product);
            }
            parser.close();
            return products;

        }catch (IOException ex){
            throw new RuntimeException("Gagal untuk memparsing CSV File" + ex.getMessage());
        }
    }


}
