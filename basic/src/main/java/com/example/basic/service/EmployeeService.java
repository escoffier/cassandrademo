package com.example.basic.service;

import com.example.basic.model.Employee;
import com.example.basic.model.MigrationResponse;
import com.example.basic.repository.nosql.CassandraEmployeeRepository;
import com.example.basic.repository.rdbms.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraBatchOperations;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Service
public class EmployeeService {

    static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    private final CassandraEmployeeRepository cassandraEmployeeRepository;

    private final EmployeeRepository employeeRepository;

    private final CassandraOperations cassandraOperations;

    private Migration<Employee, Long> employeeMigration;

    @Autowired
    public EmployeeService(CassandraEmployeeRepository cassandraEmployeeRepository, EmployeeRepository employeeRepository, CassandraOperations cassandraOperations) {
        this.cassandraEmployeeRepository = cassandraEmployeeRepository;
        this.employeeRepository = employeeRepository;
        this.cassandraOperations = cassandraOperations;
    }

    @PostConstruct
    public void SetUpMigration() {
        employeeMigration = new Migration<Employee, Long>(employeeRepository, cassandraOperations);
    }

    public Optional<Employee> mysql2cassandra(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        logger.info(employee.toString());
        employee.ifPresent(emp -> {
            cassandraEmployeeRepository.insert(emp);
        });
        return employee;
    }

    public Optional<Employee> getEmployeeFromNosql(Long id) {
        Employee employee = cassandraOperations.selectOneById(id, Employee.class);
        return Optional.ofNullable(employee);
    }

    public Optional<Employee> getEmployeeFromRdbms(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmp1(Long id) {
        return cassandraEmployeeRepository.findById(id);
    }


    @Async
    public MigrationResponse migrate() {

        CompletableFuture<Long> cnt = new CompletableFuture<>();

        Executors.newSingleThreadExecutor().submit(() -> {
            long count = employeeRepository.count();
            cnt.complete(count);

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
        });

//        Long count = employeeRepository.count();
//        final int pageSize = 500;
//        long pageCount = count / pageSize;
//
//        PageRequest pageRequest = PageRequest.of(20, 25 , Sort.by("employeeNo").ascending());
//
////        employeeRepository.findAll(pageRequest).forEach(emp -> {
////            logger.info(emp.toString());
////            cassandraEmployeeRepository.insert(emp);
////        });
//
//        Page<Employee> page = employeeRepository.findAll(pageRequest);
//
//        CassandraBatchOperations batchOperations =  cassandraOperations.batchOps();
//        batchOperations.insert(page);
//        batchOperations.execute();

        Long c = 0l;
        try {
            c = cnt.get();
        } catch (Exception ex) {
            logger.warn("get count exception", ex);
        }

        MigrationResponse response = new MigrationResponse(c, "Successful");
        return response;
    }

    private void save2Cassandra(int page, final int pageSize) {
        logger.info("page {}, pageSize {}", page, pageSize);
        PageRequest pageRequest = PageRequest.of(page, pageSize, Sort.by("employeeNo").ascending());
        Page<Employee> employeePage = employeeRepository.findAll(pageRequest);

        CassandraBatchOperations batchOperations = cassandraOperations.batchOps();
        batchOperations.insert(employeePage);
        batchOperations.execute();
    }
}
