package com.avimtoo.springamigoscode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrolements")
public class Enrolment {


  @EmbeddedId
  private EnrolmentId id;

  @ManyToOne
  @JsonIgnore
  @MapsId("studentId")
  @JoinColumn(name = "student_id",foreignKey = @ForeignKey(name = "enrolement_student_id_fk"))
  private Student student;

    @ManyToOne
    @JsonIgnore
    @MapsId("courseId")
    @JoinColumn(
            name = "course_id",
            foreignKey = @ForeignKey(name = "enrolement_course_id_fk")
    )
    private Course course;


    @Column(
            name = "created_at",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDate createdAt;

    // Constructeurs
    public Enrolment(EnrolmentId id,Student student, Course course, LocalDate createdAt) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.createdAt = createdAt;
    }

    public Enrolment(){}

    // getters and setters

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

    public EnrolmentId getId() {
        return id;
    }

    public void setId(EnrolmentId id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
