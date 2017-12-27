package com.software.demo.Repository;

import com.software.demo.Entity.Employee;
import com.software.demo.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee ,Integer>{

    //名字模糊查询
    Employee findByAccountAndPassword(String name,String password);

    Employee findByPhoneAndPassword(String phone,String password);

    Employee findByEmailAndPassword(String email,String password);

    Employee findByAccount(String account);

    Employee findByName(String account);
}
