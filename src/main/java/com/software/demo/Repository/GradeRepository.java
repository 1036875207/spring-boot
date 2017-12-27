package com.software.demo.Repository;

import com.software.demo.Entity.Daily;
import com.software.demo.Entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.GenericArrayType;
import java.util.List;

public interface GradeRepository extends JpaRepository<Grade,Integer>{
    List<Grade> findByGradeLike(String name);
    List<Grade> findByStudent_id(Integer id);
    List<Grade> findByCourse_Id(Integer id);
}
