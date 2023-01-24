package com.avimtoo.springamigoscode.controllers;

import com.avimtoo.springamigoscode.entities.StudentIdCard;
import com.avimtoo.springamigoscode.repositories.StudentIdCardRepository;
import com.avimtoo.springamigoscode.services.StudentIdCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/api/v1/studentIdCard")
public class StudentIdCardController {

    @Resource
    private StudentIdCardRepository studentIdCardRepository;
    private final StudentIdCardService studentIdCardService;


    @Autowired
    public StudentIdCardController(StudentIdCardService studentIdCardService){
        this.studentIdCardService=studentIdCardService;
    }

    @GetMapping("/studentIdCards")
    public List<StudentIdCard> getAll(){
        return  studentIdCardService.getAllStudentIdCard();
    }

    @GetMapping("/studentIdCards/{id}")
    public Optional<StudentIdCard> getOne(@PathVariable("id") Long id){
        return studentIdCardService.getOne(id);
    }

    @PostMapping("/studentIdCards/{id}")
    public StudentIdCard createStudentIdCard(@RequestBody StudentIdCard studentIdCard,
                                             @PathVariable("id") Long id){
        return studentIdCardService.addStudentIdCard(studentIdCard,id);
    }

    @DeleteMapping("/studentIdCards/{id}")
    public void deleteStudentIdCard(@PathVariable("id") Long id){
        studentIdCardService.deleteStudentIdCard(id);
    }

    @PutMapping("/studentIdCards/{id}")
    public StudentIdCard updateStudentIdCard(@RequestBody StudentIdCard studentIdCard,
                                             @PathVariable("id") Long id){
        return studentIdCardService.updateStudentIdCard(studentIdCard,id);
    }
}
