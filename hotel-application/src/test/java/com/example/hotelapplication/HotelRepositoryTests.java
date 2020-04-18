package com.example.hotelapplication;

import com.example.hotelapplication.config.CassandraConfig;
import com.example.hotelapplication.model.Address;
import com.example.hotelapplication.model.Hotels;
import com.example.hotelapplication.repository.HotelRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CassandraConfig.class)
//@DataJpaTest
//@ContextConfiguration(classes = CassandraConfig.class)
public class HotelRepositoryTests {

    @Autowired
    HotelRepository hotelRepository;

    @Test
    public void findSavedUserById() {

        Address address = new Address();
        address.setCountry("china");
        address.setState_or_province("anhui");
        address.setCity("hefei");

        Set<String> pois = new HashSet<String>( );
        pois.add("poi-1");
        pois.add("poi-2");

        Hotels hotel = new Hotels("AZ123", address, "AZ123-name", "12345",  pois);
        hotel = hotelRepository.save(hotel);

        assertThat(hotelRepository.findById(hotel.getId())).contains(hotel);
    }

}
