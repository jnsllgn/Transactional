package com.example.transaction;

import com.example.transaction.entity.Rekening;
import com.example.transaction.repo.RekeningRepo;
import com.example.transaction.service.RekeningService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TransaksiTest.class})
public class TransaksiTest {

    @Mock
    RekeningRepo rekeningRepo;

    @InjectMocks
    RekeningService rekeningService;

    public List<Rekening> rekening;

    @Test
    @Order(1)
    public void test_getTrans() {
        List <Rekening> rekening = new ArrayList<Rekening>();

        rekening.add(new Rekening("1188", "donni", 1000));
        rekening.add(new Rekening("1199", "donni", 1000));

        when(rekeningRepo.findAll()).thenReturn(rekening);
        assertEquals(2, rekeningService.findAll().size());
    }

}
