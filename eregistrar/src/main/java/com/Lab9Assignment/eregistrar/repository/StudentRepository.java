package com.Lab9Assignment.eregistrar.repository;

import com.Lab9Assignment.eregistrar.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByStudentNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String studentNumber, String firstName, String lastName);
}
