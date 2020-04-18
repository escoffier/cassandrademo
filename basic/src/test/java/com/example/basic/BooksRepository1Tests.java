package com.example.basic;

import com.example.basic.config.CassandraConfig;
import com.example.basic.model.Book;
import com.example.basic.repository.nosql.BooksRepository;
import org.cassandraunit.spring.CassandraDataSet;
import org.cassandraunit.spring.CassandraUnitTestExecutionListener;
import org.cassandraunit.spring.EmbeddedCassandra;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.context.TestExecutionListeners.MergeMode.MERGE_WITH_DEFAULTS;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes = CassandraConfig.class)
@SpringBootTest(classes = CassandraConfig.class)
@TestExecutionListeners(
        listeners = CassandraUnitTestExecutionListener.class,
        mergeMode = MERGE_WITH_DEFAULTS
)
@CassandraDataSet
@EmbeddedCassandra
public class BooksRepository1Tests {

    @Autowired
    BooksRepository repository;

//    @Autowired
//    CassandraTemplate cassandraTemplate;

    @Test
    public void findSavedUserById() {

        Book book = new Book();
        UUID uuid = UUID.randomUUID();
        book.setId(uuid);
        book.setTitle("book title");
        book.setPublisher("book publisher");

        book = repository.save(book);

        assertThat(repository.findById(book.getId())).contains(book);
    }
}
