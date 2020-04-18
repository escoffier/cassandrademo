package com.example.basic.repository.rdbms;


import com.example.basic.model.SalariesEntity;
import com.example.basic.model.SalariesEntityPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalaryRepository extends JpaRepository<SalariesEntity, SalariesEntityPK> {

    List<SalariesEntity> findByempNo(Long id);
}
