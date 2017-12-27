package com.software.demo.Entity;

import javax.persistence.*;

@Entity
public class Grade {

    @Id
    @GeneratedValue
    private Integer id;
    private String grade;
    private Long date;
    @ManyToOne()
    @JoinColumn(name = "student_id",foreignKey = @ForeignKey(name = "fk_student_fc"))
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "course_id")
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
