package com.avimtoo.springamigoscode.services;

import com.avimtoo.springamigoscode.entities.Course;
import com.avimtoo.springamigoscode.entities.Enrolment;
import com.avimtoo.springamigoscode.entities.EnrolmentId;
import com.avimtoo.springamigoscode.entities.Student;
import com.avimtoo.springamigoscode.exception.ResourceNotFoundException;
import com.avimtoo.springamigoscode.repositories.CourseRepository;
import com.avimtoo.springamigoscode.repositories.EnrolmentRepository;
import com.avimtoo.springamigoscode.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolmentService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    private final EnrolmentRepository enrolmentRepository;


    @Autowired
    public  EnrolmentService(EnrolmentRepository enrolmentRepository){
        this.enrolmentRepository = enrolmentRepository;
    }

    public List<Enrolment> getAllEnrolment(){
        return enrolmentRepository.findAll();
    }

   public Optional<Enrolment> getSingleEnrolment(EnrolmentId enrolmentId){
        return enrolmentRepository.findById(enrolmentId);
    }

    public Enrolment addEnrolment(Enrolment enrolment,Long id, Long id_course){
        Student student = studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Error lors de la recuperation de l'etudiant"));
        Course course = courseRepository.findById(id_course).orElseThrow(() ->
                new ResourceNotFoundException("Error lors de la recuperation du cours"));
        enrolment.setId(new EnrolmentId(student.getId(),course.getId()));
        enrolment.setStudent(student);
        enrolment.setCourse(course);
        Enrolment enrolment1= enrolmentRepository.save(enrolment);
        course.addEnrolments(enrolment1);
        courseRepository.save(course);
        student.addEnrolments(enrolment1);
        studentRepository.save(student);
        return enrolment1;


    }
    public void deleteEnrolment(EnrolmentId enrolmentId){
         enrolmentRepository.findById(enrolmentId).orElseThrow(()->
                 new ResourceNotFoundException("impossible de supprimer cet element"));
         enrolmentRepository.deleteById(enrolmentId);
    }

    public Enrolment updateEnrlment(Enrolment enrolment,EnrolmentId enrolmentId){
        Enrolment enrolment1 = enrolmentRepository.findById(enrolmentId).orElseThrow(()->
                new ResourceNotFoundException("impossible de modifer cet element"));
        enrolment1.setCreatedAt(enrolment.getCreatedAt());
        return  enrolmentRepository.saveAndFlush(enrolment1);
        }

}
