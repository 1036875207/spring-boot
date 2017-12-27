package com.software.demo.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class News {

    @Id
    @GeneratedValue
    private Integer id;
    private Integer inputdate;
    private String context;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getInputdate() {
        return inputdate;
    }

    public void setInputdate(Integer inputdate) {
        this.inputdate = inputdate;
    }
}
