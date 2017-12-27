package com.software.demo.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Course {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "employee_id",foreignKey = @ForeignKey(name = "fk_employee_fc"))
    private Employee employee;
    private String decrib;
    private Integer time;
    private Long inputdate;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "course")
    List<Grade> Grade;

    public List<com.software.demo.Entity.Grade> getGrade() {
        return Grade;
    }

    public void setGrade(List<com.software.demo.Entity.Grade> grade) {
        Grade = grade;
    }

    public Long getInputdate() {
        return inputdate;
    }

    public void setInputdate(Long inputdate) {
        this.inputdate = inputdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDecrib() {
        return decrib;
    }

    public void setDecrib(String decrib) {
        this.decrib = decrib;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
