package com.avimtoo.springamigoscode.repositories;

import com.avimtoo.springamigoscode.entities.Enrolment;
import com.avimtoo.springamigoscode.entities.EnrolmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Long> {
    Optional<Enrolment> findById(EnrolmentId enrolmentId);

    void deleteById(EnrolmentId enrolmentId);


}
