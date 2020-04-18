package com.example.basic.repository.nosql;

import com.example.basic.model.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface CassandraEmployeeRepository extends CassandraRepository<Employee, Long> {
}
