package com.example.basic.config;

import com.example.basic.model.Employee;
import com.example.basic.model.SalariesEntity;
import com.example.basic.model.SalariesEntityPK;
import com.example.basic.repository.rdbms.EmployeeRepository;
import com.example.basic.repository.rdbms.SalaryRepository;
import com.example.basic.service.Migration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class AppConfig {

    @Autowired
    private SalaryRepository salaryRepository;

    @Autowired
    private CassandraOperations cassandraOperations;

    @Bean("salary-migration")
    public Migration getMigration() {
        return new Migration<SalariesEntity, SalariesEntityPK>(salaryRepository, cassandraOperations);
    }

    @Bean("employee-migration")
    public Migration getEmpMigration(EmployeeRepository employeeRepository, CassandraOperations cassandraOperations) {
        return new Migration<Employee, Long>(employeeRepository, cassandraOperations);
    }
}
