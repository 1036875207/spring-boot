package com.software.demo.Repository;

import com.software.demo.Entity.Employee;
import com.software.demo.Entity.Positon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Positon,Integer>{


}
