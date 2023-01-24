package com.avimtoo.springamigoscode.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;


@Entity
@Table(
        name =" students")
public class Student {

    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "student_sequence"
    )
    @Column(
            name = "id"
    )
    private  Long id;
    @Column(name = "first_name",nullable = false,columnDefinition = "TEXT")
    private  String firstName;

    @Column(name = "last_name",nullable = false,columnDefinition = "TEXT")
    private  String lastName;

    @Column(nullable = false,name = "email",columnDefinition = "TEXT")
    private String email;
    @Column(nullable = false)
    private LocalDate dob;

    @Transient
    @Column(nullable = false,name = "age")
    private Integer age;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "student_id_card")
    private StudentIdCard studentIdCard;

    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();

    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade ={CascadeType.PERSIST,CascadeType.REMOVE},
            fetch =FetchType.LAZY

    )
    private List<Enrolment> enrolments = new ArrayList<>();


// constructeurs

    public Student(String firstName, String email, LocalDate dob) {
        this.firstName = firstName;
        this.email = email;
        this.dob = dob;
    }

    public Student(){}

    //getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Integer getAge() {
        return Period.between(this. dob,LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentIdCard getStudent() {
        return studentIdCard;
    }

    public void setStudentIdCard(StudentIdCard studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public List<Enrolment> getEnrolments() {
        return enrolments;
    }

    public void addEnrolments(Enrolment enrolment) {
        if(!enrolments.contains(enrolment))
        enrolments.add(enrolment);
    }

    public void removeEnrolments(Enrolment enrolment){
        enrolments.remove(enrolment);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    public void addBook(Book book) {
        if(!books.contains(book)) {
            books.add(book);
        }
    }
    public void removeBook(Book book){
            books.remove(book);
    }

    // toString


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", age=" + age +
                '}';
    }
}
