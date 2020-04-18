package com.example.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

//@Configuration
//public class CassandraConfig extends AbstractCassandraConfiguration {
//    @Override
//    protected String getKeyspaceName() {
//        return "k_books";
//    }
//
//    @Bean
//    public CassandraClusterFactoryBean clusterFactoryBean() {
//        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
//        cluster.setJmxReportingEnabled(false);
//        cluster.setContactPoints("172.19.0.2");
//        cluster.setPort(9042);
//        return cluster;
//    }
//
//    @Bean
//    public CassandraMappingContext mappingContext() {
//        return new CassandraMappingContext();
//    }
//}

@Configuration
@EnableCassandraRepositories(basePackages = "com.example.basic.repository.nosql")
public class CassandraConfig{

}
