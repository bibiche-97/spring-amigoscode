package com.avimtoo.springamigoscode.repositories;

import com.avimtoo.springamigoscode.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // select * from students where email = ?
    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);

    Optional<Student> findByFirstName(String firstName);
    Optional<Student> findByLastName(String lastName);

}
