package com.avimtoo.springamigoscode.controllers;

import com.avimtoo.springamigoscode.entities.Book;
import com.avimtoo.springamigoscode.entities.Student;
import com.avimtoo.springamigoscode.repositories.BookRepository;
import com.avimtoo.springamigoscode.repositories.StudentRepository;
import com.avimtoo.springamigoscode.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    private final StudentService studentService;
  @Autowired
    private  StudentRepository studentRepository;

 @Autowired
  private BookRepository bookRepository;


@Autowired
    public StudentController(StudentService studentService){
        this.studentService= studentService;
    }

    @GetMapping("/students")
    public List<Student> getAll(){
        return  studentService.getAllStudents();
    }

    @GetMapping("/students/{id}")
    public Optional<Student> getOne(@PathVariable("id") Long id){
        return studentService.findStudentById(id);
    }

    @PostMapping("/students")
    public  Student addStudent(@RequestBody Student student){
        return studentService.addSingleStudent(student);
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") Long id){
         studentService.deleteStudent(id);
    }
    @PutMapping("/students/{id}")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") Long id){
    return studentService.updateStudent(student,id);
    }
}

