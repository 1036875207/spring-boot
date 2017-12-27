package com.software.demo.Repository;

import com.software.demo.Entity.Daily;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DailyRepository extends JpaRepository<Daily,Integer>{

     List<Daily> findByEmployeeid(Integer id);
}
