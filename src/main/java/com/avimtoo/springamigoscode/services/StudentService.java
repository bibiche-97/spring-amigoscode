package com.avimtoo.springamigoscode.services;

import com.avimtoo.springamigoscode.entities.Student;
import com.avimtoo.springamigoscode.exception.IllegalStateException;
import com.avimtoo.springamigoscode.exception.ResourceNotFoundException;
import com.avimtoo.springamigoscode.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {



    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    public  Optional<Student> findStudentById(Long id){
        return studentRepository.findById(id);
    }

    public Student addSingleStudent(Student student){


        if(student.getFirstName()!= null && student.getLastName()!=null
                && student.getEmail()!=null && student.getDob()!=null)
        {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
            if(studentOptional.isPresent()){
                throw new IllegalStateException("Oups! Cette email existe deja. Veuillez ajouter une autre ");
            }
            return  studentRepository.save(student);
        }
      return null;

    }


    public void deleteStudent(Long id){
        studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Oups! Impossible de supprimer car cet etudiant n'existe pas."));
        studentRepository.deleteById(id);
    }
    @Transactional
    public Student updateStudent(Student student,Long id){
        Student student1 = studentRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Oups! Impossible de modifier car cette etudiant n'existe pas."));
          if(student.getFirstName()!=null && student.getFirstName().length() > 0
                  && !Objects.equals(student1.getFirstName(),student.getFirstName())){
              student1.setFirstName(student.getFirstName());
          }
        if(student.getLastName()!=null && student.getLastName().length() > 0 && !Objects.equals(student1.getLastName(),student.getLastName())){
            student1.setLastName(student.getLastName());
        }

        student1.setDob(student.getDob());

          if(student.getEmail()!=null && student.getEmail().length()>0
                  && !Objects.equals(student1.getEmail(),student.getEmail())) {
              Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
              if(studentOptional.isPresent()){
                  throw new IllegalStateException("Cette email existe deja");
              }
              student1.setEmail(student.getEmail());
          }
            return studentRepository.saveAndFlush(student1);

    }

}
