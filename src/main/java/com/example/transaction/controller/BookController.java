package com.example.transaction.controller;

import com.example.transaction.entity.Book;
import com.example.transaction.entity.Rekening;
import com.example.transaction.service.BookServiceUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.Cacheable;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookServiceUpdate bookServiceUpdate;

    @PostMapping("/book")
    public Book addBook(@RequestBody Book book){
        return bookServiceUpdate.addBook(book);
    }
    @GetMapping("/book")
    @Cacheable("")
    public List<Book> getBooksfindAll() {

        return bookServiceUpdate.findAll();
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        return bookServiceUpdate.updateBook(book);
    }

    @GetMapping("/book/{id}")
    public Book getBook(@PathVariable long id){
        return bookServiceUpdate.getBook(id);
    }
    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable long id){
        return bookServiceUpdate.deleteBook(id);
    }
}
