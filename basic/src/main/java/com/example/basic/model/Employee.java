package com.example.basic.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@org.springframework.data.cassandra.core.mapping.Table(value = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 10232L;

    //    @PrimaryKey(name = "emp_no", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    @PrimaryKey(value = "emp_no")
    @Id
    @javax.persistence.Column(name = "emp_no")
    @org.springframework.data.cassandra.core.mapping.Column(value = "emp_no")
    private Long employeeNo;

    @javax.persistence.Column(name = "birth_date")
    @org.springframework.data.cassandra.core.mapping.Column(value = "birth_date")
    private LocalDate birthDate;

    @javax.persistence.Column(name = "first_name")
    @org.springframework.data.cassandra.core.mapping.Column(value = "first_name")
    private String firstName;

    @javax.persistence.Column(name = "last_name")
    @org.springframework.data.cassandra.core.mapping.Column(value = "last_name")
    private String lastName;

    @javax.persistence.Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @javax.persistence.Column(name = "hire_date")
    @org.springframework.data.cassandra.core.mapping.Column(value = "hire_date")
    private LocalDate hireDate;

    public Long getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(Long employeeNo) {
        this.employeeNo = employeeNo;
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @JsonDeserialize(using = LocalDateDeserializer.class)
    public LocalDate getHireDate() {
        return hireDate;
    }

    @JsonSerialize(using = LocalDateSerializer.class)
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeNo=" + employeeNo +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", hireDate=" + hireDate +
                '}';
    }
}
