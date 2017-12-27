package com.software.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Permission {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String decribe;
    @ManyToMany(mappedBy = "permissions")
    private List<Positon> positonList;

    public List<Positon> getPositonList() {
        return positonList;
    }

    public void setPositonList(List<Positon> positonList) {
        this.positonList = positonList;
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

    public String getDecribe() {
        return decribe;
    }

    public void setDecribe(String decribe) {
        this.decribe = decribe;
    }
}
