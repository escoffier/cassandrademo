package com.example.hotelapplication.service;

import com.example.hotelapplication.model.Hotels;
import com.example.hotelapplication.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public class HotelsService {

    @Autowired
    private HotelRepository hotelRepository;

    Optional<Hotels> getHotel(@NotNull String id) {
        return hotelRepository.findById(id);
    }
}
