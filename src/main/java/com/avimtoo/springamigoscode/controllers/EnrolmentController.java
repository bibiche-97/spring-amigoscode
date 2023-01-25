package com.avimtoo.springamigoscode.controllers;

import com.avimtoo.springamigoscode.entities.Enrolment;
import com.avimtoo.springamigoscode.entities.EnrolmentId;
import com.avimtoo.springamigoscode.repositories.EnrolmentRepository;
import com.avimtoo.springamigoscode.services.EnrolmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/api/v1/enrolment")
public class EnrolmentController {

    /*@Autowired
    private EnrolmentRepository enrolmentRepository;*/

    private final EnrolmentService enrolmentService;

    @Autowired
    public  EnrolmentController(EnrolmentService enrolmentService){
        this.enrolmentService = enrolmentService;
    }

    @GetMapping("/enrolments")
    public List<Enrolment> getAll(){
        return  enrolmentService.getAllEnrolment();
    }

   @GetMapping("/enrolments/{id}/{id_course}")
    public Optional<Enrolment> getOne(@PathVariable("id") Long id,@PathVariable("id_course") Long id_course){

        return  enrolmentService.getSingleEnrolment(new EnrolmentId(id, id_course));
    }

    @PostMapping("/enrolments/{id}/{id_course}")
    public Enrolment addEnrolement(@RequestBody Enrolment enrolment,
                                   @PathVariable("id") Long id,
                                    @PathVariable("id_course") Long id_course){
        return  enrolmentService.addEnrolment(enrolment,id,id_course);
    }
    @DeleteMapping("/enrolments/{id}/{id_course}")
    public void delete(@PathVariable("id") Long id,
                       @PathVariable("id_course") Long id_course){
        enrolmentService.deleteEnrolment(new EnrolmentId(id,id_course));
    }

    @PutMapping("/enrolments/{id}/{id_course}")
    public Enrolment update(@RequestBody Enrolment enrolment,
                            @PathVariable("id") Long id,
                            @PathVariable("id_course") Long id_course)
    {
        return enrolmentService.updateEnrlment(enrolment,new EnrolmentId(id,id_course));
    }
}
