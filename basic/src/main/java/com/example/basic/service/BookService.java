package com.example.basic.service;

import com.example.basic.model.Book;
import com.example.basic.repository.nosql.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    @Autowired
    private BooksRepository booksRepository;

    public Optional<Book> get(UUID id) {
        return booksRepository.findById(id);
    }

}
