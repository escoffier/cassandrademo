package com.example.basic.service;

import com.example.basic.model.Employee;
import com.example.basic.model.MigrationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;


public class Migration<E, K> {

    static final Logger logger = LoggerFactory.getLogger(Migration.class);

    private JpaRepository repository;

    private CassandraOperations cassandraOperations;


    public Migration(JpaRepository repository, CassandraOperations cassandraOperations) {
        this.repository = repository;
        this.cassandraOperations = cassandraOperations;
    }


    public MigrationResponse migrate() {
        long count = repository.count();
        doMigrate(count);
        MigrationResponse response = new MigrationResponse(count, "Successful");
        return response;
    }

    @Async
    public void doMigrate(long count) {
        final int pageSize = 25;
        long pageCount = count / pageSize;

        long remainder = (count % pageSize);

        logger.debug("pageCount: {}, remainder: {}", pageCount, remainder);

        for (int i = 0; i < pageCount; i++) {
            save2Cassandra(i, pageSize);
        }

        if (remainder > 0) {
            save2Cassandra((int) count, (int) remainder);
        }
    }

    private void save2Cassandra(int page, final int pageSize) {
        logger.info("page {}, pageSize {}", page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("employeeNo").ascending());
        Page<Employee> employeePage = repository.findAll(pageRequest);

        CassandraBatchOperations batchOperations = cassandraOperations.batchOps();
        batchOperations.insert(employeePage);
        batchOperations.execute();
    }

}
