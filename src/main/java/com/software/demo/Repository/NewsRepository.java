package com.software.demo.Repository;

import com.software.demo.Entity.Daily;
import com.software.demo.Entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Integer>{
}
