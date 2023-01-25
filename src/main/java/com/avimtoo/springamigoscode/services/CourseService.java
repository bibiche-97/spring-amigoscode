package com.avimtoo.springamigoscode.services;

import com.avimtoo.springamigoscode.entities.Course;
import com.avimtoo.springamigoscode.exception.ResourceNotFoundException;
import com.avimtoo.springamigoscode.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

   @Autowired
    public CourseService(CourseRepository courseRepository){
        this.courseRepository=courseRepository;
    }

    public List<Course> getAllCourse(){
       return  courseRepository.findAll();
    }

    public Optional<Course> getSingleCourse(Long id){
       return  courseRepository.findById(id);
    }

    public Course addCourse(Course course){
       return courseRepository.save(course);
    }

    public Course updateCourse(Course course, Long id){
      Course course1= courseRepository.findById(id).orElseThrow(()->
              new ResourceNotFoundException("impossibe de mettre a jour un cours qui n'existe pas "));
      course1.setDepartment(course.getDepartment());
      course1.setName(course.getName());
       return courseRepository.save(course1);
    }

    public void deleteCourse(Long id){
       courseRepository.findById(id).orElseThrow(() ->
               new ResourceNotFoundException("impossible de supprimer un utilisateur qui n'existe pas "));
                courseRepository.deleteById(id);
    }

}
