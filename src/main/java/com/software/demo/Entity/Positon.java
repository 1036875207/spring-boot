package com.software.demo.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Positon {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String apartment;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "position")
    private List<Employee> employees;
    @ManyToMany()
    @JoinTable(name = "Position_Permission",
            joinColumns = { @JoinColumn(name = "Position_ID", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "Permission_ID", referencedColumnName = "id") })
    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
