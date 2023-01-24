package com.avimtoo.springamigoscode.repositories;

import com.avimtoo.springamigoscode.entities.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentIdCardRepository extends JpaRepository<StudentIdCard, Long> {
}
