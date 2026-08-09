package com.Lab9Assignment.eregistrar.service;

import com.Lab9Assignment.eregistrar.model.Student;
import com.Lab9Assignment.eregistrar.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long studentId) {
        return studentRepository.findById(studentId);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public List<Student> search(String query) {
        if (query == null || query.isBlank()) {
            return findAll();
        }
        return studentRepository
                .findByStudentNumberContainingIgnoreCaseOrFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        query, query, query);
    }
}
