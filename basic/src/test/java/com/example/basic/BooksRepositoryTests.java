package com.example.basic;

import com.datastax.driver.core.Session;
import com.example.basic.model.Book;
import com.example.basic.repository.nosql.BooksRepository;
import com.example.util.CassandraKeyspace;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Version;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksRepositoryTests {

    public final static Version CASSANDRA_3_4 = Version.parse("3.4");
    @ClassRule
    public final static CassandraKeyspace CASSANDRA_KEYSPACE = CassandraKeyspace.onLocalhost();


    @Autowired
    BooksRepository repository;
    @Autowired
    Session session;
    Book book;
    @Before
    public void setUp() {

        book = new Book();
        UUID uuid = UUID.randomUUID();
        book.setId(uuid);
        book.setTitle("book title");
        book.setPublisher("book publisher");
    }

    @Test
    public void findSavedUserById() {

        book = repository.save(book);

        assertThat(repository.findById(book.getId())).contains(book);
    }

}
