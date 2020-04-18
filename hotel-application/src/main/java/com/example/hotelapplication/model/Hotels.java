package com.example.hotelapplication.model;

import com.google.common.base.Objects;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Set;

@Table(value = "hotels")
public class Hotels {

    @PrimaryKey(value = "id")
    String id;

    Address address;
    String name;
    String phone;
    Set<String> pois;

    public Hotels(String id, Address address, String name, String phone, Set<String> pois) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.pois = pois;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<String> getPois() {
        return pois;
    }

    public void setPois(Set<String> pois) {
        this.pois = pois;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotels hotels = (Hotels) o;
        return Objects.equal(id, hotels.id) &&
                Objects.equal(address, hotels.address) &&
                Objects.equal(name, hotels.name) &&
                Objects.equal(phone, hotels.phone) &&
                Objects.equal(pois, hotels.pois);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, address, name, phone, pois);
    }

    @Override
    public String toString() {
        return "Hotels{" +
                "id='" + id + '\'' +
                ", address=" + address +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", pois=" + pois +
                '}';
    }
}
