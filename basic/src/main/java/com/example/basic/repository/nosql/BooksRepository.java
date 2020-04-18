package com.example.basic.repository.nosql;


import com.example.basic.model.Book;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface BooksRepository extends CassandraRepository<Book, UUID> {

}
