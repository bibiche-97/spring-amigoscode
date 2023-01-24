package com.avimtoo.springamigoscode.services;

import com.avimtoo.springamigoscode.entities.Student;
import com.avimtoo.springamigoscode.entities.StudentIdCard;
import com.avimtoo.springamigoscode.exception.ResourceNotFoundException;
import com.avimtoo.springamigoscode.repositories.StudentIdCardRepository;
import com.avimtoo.springamigoscode.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentIdCardService {

   @Autowired
    private StudentRepository studentRepository;
    private final StudentIdCardRepository studentIdCardRepository;

    @Autowired
    public  StudentIdCardService(StudentIdCardRepository studentIdCardRepository){
        this.studentIdCardRepository=studentIdCardRepository;
    }

    public List<StudentIdCard> getAllStudentIdCard(){
        return studentIdCardRepository.findAll();
    }

    public Optional<StudentIdCard> getOne(Long id){
        return studentIdCardRepository.findById(id);
    }

    public StudentIdCard addStudentIdCard(StudentIdCard studentIdCard,Long id){
        Student student = studentRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("Failled to load student"));
        studentIdCard.setStudent(student);
        StudentIdCard studentIdCard1 = studentIdCardRepository.save(studentIdCard);
        student.setStudentIdCard(studentIdCard1);
        return studentIdCard1;
    }
    public void deleteStudentIdCard(Long id){
        studentIdCardRepository.findById(id).orElseThrow(()
        -> new ResourceNotFoundException("deleted failled"));
        studentIdCardRepository.deleteById(id);
    }
    public StudentIdCard updateStudentIdCard(StudentIdCard studentIdCard,Long id){
        StudentIdCard studentIdCard1 = studentIdCardRepository.findById(id).orElseThrow(()
                        -> new ResourceNotFoundException("updated failled"));
        studentIdCard1.setCardNumber(studentIdCard.getCardNumber());
        return  studentIdCardRepository.saveAndFlush(studentIdCard1);
    }

}
