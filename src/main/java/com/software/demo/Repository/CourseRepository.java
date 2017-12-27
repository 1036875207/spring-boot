package com.software.demo.Repository;

import com.software.demo.Entity.Course;
import com.software.demo.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer>{
    /*public Page<Course> findByEmployee_IdAndIsDeleteOrderByShowIndexAsc(String floorId, boolean b, Pageable pageable);*/
    List<Course> findByNameLike(String name);
}
