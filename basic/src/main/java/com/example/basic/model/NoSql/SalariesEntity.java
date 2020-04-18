package com.example.basic.model.NoSql;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.sql.Date;
import java.util.Objects;

//import javax.persistence.*;

@Table(value = "salaries")
public class SalariesEntity {

    @PrimaryKey
    private SalariesEntityPK pk;

    @Column(value = "salary")
    private int salary;

    @Column(value = "to_date")
    private Date toDate;

    public int getEmpNo() {
        return pk.getEmpNo();
    }

    public void setEmpNo(int empNo) {
        setEmpNo(empNo);
    }

    public Date getFromDate() {
        return pk.getFromDate();
    }

    public void setFromDate(Date fromDate) {
        pk.setFromDate(fromDate);
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesEntity that = (SalariesEntity) o;
        return pk == that.pk &&
                salary == that.salary &&
                Objects.equals(toDate, that.toDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pk.hashCode(), salary, toDate);
    }
}
