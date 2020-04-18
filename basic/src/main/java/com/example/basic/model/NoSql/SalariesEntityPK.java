package com.example.basic.model.NoSql;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@PrimaryKeyClass
public class SalariesEntityPK implements Serializable {

    @PrimaryKeyColumn(name = "emp_no", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private int empNo;

    @PrimaryKeyColumn(name = "from_date", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private Date fromDate;

    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalariesEntityPK that = (SalariesEntityPK) o;
        return empNo == that.empNo &&
                Objects.equals(fromDate, that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }
}
