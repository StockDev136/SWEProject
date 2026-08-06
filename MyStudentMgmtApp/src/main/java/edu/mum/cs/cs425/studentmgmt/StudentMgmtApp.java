package edu.mum.cs.cs425.studentmgmt;

import edu.mum.cs.cs425.studentmgmt.model.Classroom;
import edu.mum.cs.cs425.studentmgmt.model.Student;
import edu.mum.cs.cs425.studentmgmt.model.Transcript;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class StudentMgmtApp {

    private static final String PERSISTENCE_UNIT_NAME = "studentMgmtPU";
    private final EntityManagerFactory emf;

    public StudentMgmtApp() {
        this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    }

    public void saveStudent(Student student) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            System.out.println("Saved: " + student);
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void saveStudentTranscript(Student student, Transcript transcript) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Student managedStudent = em.find(Student.class, student.getStudentId());
            if (managedStudent == null) {
                managedStudent = em.merge(student);
            }

            transcript.setStudent(managedStudent);
            managedStudent.setTranscript(transcript);
            em.persist(transcript);

            em.getTransaction().commit();
            System.out.println("Saved: " + managedStudent + " with " + transcript);
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void saveStudentClassroom(Student student, Classroom classroom) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            Classroom managedClassroom = em.find(Classroom.class, classroom.getClassroomId());
            if (managedClassroom == null) {
                em.persist(classroom);
                managedClassroom = classroom;
            }

            Student managedStudent = em.find(Student.class, student.getStudentId());
            if (managedStudent == null) {
                managedStudent = em.merge(student);
            }
            managedStudent.setClassroom(managedClassroom);
            managedClassroom.getStudents().add(managedStudent);

            em.getTransaction().commit();
            System.out.println("Saved: " + managedStudent + " in " + managedClassroom);
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public void close() {
        if (emf.isOpen()) {
            emf.close();
        }
    }

    public static void main(String[] args) {
        StudentMgmtApp app = new StudentMgmtApp();

        try {
            // ---- Task 1: create and save a single Student ----
            Student s1 = new Student(
                    1L,
                    "000-61-0001",
                    "Anna",
                    "Lynn",
                    "Smith",
                    3.45,
                    LocalDate.of(2019, 5, 24)
            );
            app.saveStudent(s1);

            Transcript t1 = new Transcript(1L, "BS Computer Science");
            app.saveStudentTranscript(s1, t1);

            Classroom c1 = new Classroom(1L, "McLaughlin building", "M105");
            app.saveStudentClassroom(s1, c1);

        } finally {
            app.close();
        }
    }
}
