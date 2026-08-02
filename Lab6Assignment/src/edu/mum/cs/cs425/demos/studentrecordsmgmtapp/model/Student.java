package edu.mum.cs.cs425.demos.studentrecordsmgmtapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private int studentId;
    private String name;
    private LocalDate dateOfAdmission;

    public static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    public Student() {
    }


    public Student(int studentId, String name, LocalDate dateOfAdmission) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfAdmission = dateOfAdmission;
    }

    public Student(int studentId, String name, String dateOfAdmission) {
        this.studentId = studentId;
        this.name = name;
        this.dateOfAdmission = LocalDate.parse(dateOfAdmission, DATE_FORMAT);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfAdmission() {
        return dateOfAdmission;
    }

    public void setDateOfAdmission(LocalDate dateOfAdmission) {
        this.dateOfAdmission = dateOfAdmission;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", dateOfAdmission=" + (dateOfAdmission == null ? "null" : dateOfAdmission.format(DATE_FORMAT)) +
                '}';
    }
}
