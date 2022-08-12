package com.example.transaction.service;

import com.example.transaction.entity.Book;
import com.example.transaction.repo.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceUpdate {
//    private static final Logger logger = LoggerFactory.getLogger(this.getClass());
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BookRepository bookRepository;

    @Cacheable("books")
    public List<Book> findAll() {
        logger.info("Semua data buku diambil dari database");
        return bookRepository.findAll();
    }


    public Book addBook(Book book) {
        logger.info("menambahkan buku dengan id - {}", book.getId());
        return bookRepository.save(book);
    }


    @CachePut(cacheNames = "books", key="#book.id")
    public Book updateBook(Book book) {
        bookRepository.updateAddress(book.getId(), book.getName());
        logger.info("buku diperbaharui dengan nama baru");
        return book;
    }


    @Cacheable(cacheNames = "books", key="#id")
    public Book getBook(long id) {
        logger.info("Data buku diambil dari database");
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return new Book();
        }
    }


    @CacheEvict(cacheNames = "books", key = "#id")
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        return "Buku dihapus";
    }
}
