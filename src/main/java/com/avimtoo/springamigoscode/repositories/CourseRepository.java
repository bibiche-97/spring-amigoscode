package com.avimtoo.springamigoscode.repositories;

import com.avimtoo.springamigoscode.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
