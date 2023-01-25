package com.avimtoo.springamigoscode.controllers;

import com.avimtoo.springamigoscode.entities.Course;
import com.avimtoo.springamigoscode.repositories.CourseRepository;
import com.avimtoo.springamigoscode.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Resource
    private CourseRepository courseRepository;
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService=courseService;
    }

    @GetMapping("/courses")
    public List<Course> getAllCourse(){
        return courseService.getAllCourse();
    }

    @GetMapping("/courses/{id}")
    public Optional<Course> getOne(@PathVariable("id") Long id){
        return courseService.getSingleCourse(id);
    }

    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course){
        return  courseService.addCourse(course);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@RequestBody Course course, @PathVariable("id") Long id){
        return  courseService.updateCourse(course,id);
    }
    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable("id") Long id){
        courseService.deleteCourse(id);
}

}
