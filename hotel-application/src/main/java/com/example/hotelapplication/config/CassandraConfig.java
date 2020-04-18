package com.example.hotelapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.example.hotelapplication.repository")
public class CassandraConfig{

}

