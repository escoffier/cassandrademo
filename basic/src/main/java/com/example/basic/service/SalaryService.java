package com.example.basic.service;

import com.example.basic.model.MigrationResponse;
import com.example.basic.model.SalariesEntity;
import com.example.basic.model.SalariesEntityPK;
import com.example.basic.repository.rdbms.SalaryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class SalaryService {
    static final Logger logger = LoggerFactory.getLogger(SalaryService.class);

    private Migration<SalariesEntity, SalariesEntityPK> salariesMigration;

    private CassandraOperations cassandraOperations;

    private SalaryRepository salaryRepository;


    @Autowired
    public SalaryService(CassandraOperations cassandraOperations, SalaryRepository salaryRepository) {
        this.cassandraOperations = cassandraOperations;
        this.salaryRepository = salaryRepository;
    }

    @Autowired
    public void setMigration(@Qualifier("salary-migration") Migration<SalariesEntity, SalariesEntityPK> migration) {
        salariesMigration = migration;

    }

//    @PostConstruct
//    public void setUpMigration() {
//        salariesMigration = new Migration<SalariesEntity, SalariesEntityPK>(salaryRepository, cassandraOperations);
//    }

    public MigrationResponse migrate() {

        CompletableFuture<Long> cnt = new CompletableFuture<>();
        long count = salaryRepository.count();
        salariesMigration.doMigrate(count);
        MigrationResponse response = new MigrationResponse(count, "Successful");
        logger.info("starting migration");
        return response;

//        return salariesMigration.migrate();
    }

    public Optional<SalariesEntity> getSalary(Long id) {
        SalariesEntityPK key = new SalariesEntityPK();
        key.setEmpNo(10036L);

        return salaryRepository.findById(key);
    }

    public List<SalariesEntity> getSalaries(Long id) {

        return salaryRepository.findByempNo(id);
//        return salaryRepository.findById(key);
    }
}
