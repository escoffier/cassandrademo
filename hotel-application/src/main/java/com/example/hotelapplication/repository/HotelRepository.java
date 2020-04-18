package com.example.hotelapplication.repository;

import com.example.hotelapplication.model.Hotels;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface HotelRepository extends CassandraRepository<Hotels, String> {
}
