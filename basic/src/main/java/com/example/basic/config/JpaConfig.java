package com.example.basic.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackages = "com.example.basic.repository.rdbms")
//@EnableCassandraRepositories(basePackages = "com.example.basic.repository.nosql")
public class JpaConfig {
}
