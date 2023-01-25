package com.avimtoo.springamigoscode.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity(name = "Book")
@Table(name = "books")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "book_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    private LocalDate createdAt;

    @Column(
            name = "book_name"
    )
    private String bookName;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(
            name = "student_id",
            nullable = true,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_book_fk"
            )
    )
    private Student student;

    // constructeur
    public Book() {
    }

    public Book(String bookName,
                LocalDate createdAt) {
        this.createdAt = createdAt;
        this.bookName = bookName;
    }

    // getters & setters
    public Long getId() {
        return id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public Student getStudent() {
        return student;
    }

    public String getBookName() {
        return bookName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    //toString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\'' +
                ", student=" + student +
                '}';
    }
}
