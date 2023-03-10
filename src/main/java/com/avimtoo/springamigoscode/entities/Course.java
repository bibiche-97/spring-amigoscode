package com.avimtoo.springamigoscode.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @SequenceGenerator(
        name = "course_sequence",
        sequenceName = "course_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "course_sequence"
    )
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT")
    private String name;
    @Column(
            name = "department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy ="course"
    )
    private List<Enrolment> enrolments = new ArrayList<>();


    // constructeurs
    public Course( String name, String department) {
        this.name = name;
        this.department = department;
    }

    public Course(){}

    // getters & setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void setEnrolments(List<Enrolment> enrolments) {
        this.enrolments = enrolments;
    }
    public void addEnrolments(Enrolment enrolment){
        if(!enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }

    }
    public void removeEnrolement(Enrolment enrolment){
  enrolments.remove(enrolment);
    }
}
