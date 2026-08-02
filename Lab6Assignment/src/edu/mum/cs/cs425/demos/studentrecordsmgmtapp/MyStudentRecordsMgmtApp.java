package edu.mum.cs.cs425.demos.studentrecordsmgmtapp;

import edu.mum.cs.cs425.demos.studentrecordsmgmtapp.model.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Executable class for the Student Records Management App.
 *
 * Lab Assignment 6 - CS425
 */
public class MyStudentRecordsMgmtApp {

    /**
     * A student is considered a "Platinum Alumni" if they were admitted
     * into the University at least this many years ago.
     */
    private static final int PLATINUM_ALUMNI_MIN_YEARS = 30;

    public static void main(String[] args) {

        Student[] students = new Student[]{
                new Student(110001, "Dave", "11/18/1951"),
                new Student(110002, "Anna", "12/07/1990"),
                new Student(110003, "Erica", "01/31/1974"),
                new Student(110004, "Carlos", "08/22/2009"),
                new Student(110005, "Bob", "03/05/1990")
        };


        System.out.println("=========================================");
        System.out.println(" All Students (ascending order by name)");
        System.out.println("=========================================");
        printListOfStudents(students);

        System.out.println();
        System.out.println("=========================================");
        System.out.println(" Platinum Alumni Students (>= " + PLATINUM_ALUMNI_MIN_YEARS
                + " years since admission), descending by dateOfAdmission");
        System.out.println("=========================================");
        List<Student> platinumAlumni = getListOfPlatinumAlumniStudents(students);
        platinumAlumni.sort(Comparator.comparing(Student::getDateOfAdmission).reversed());
        for (Student s : platinumAlumni) {
            System.out.println(s);
        }

        System.out.println();
        System.out.println("=========================================");
        System.out.println(" printHelloWorld([3, 5, 7, 10, 14, 35, 20])");
        System.out.println("=========================================");
        printHelloWorld(new int[]{3, 5, 7, 10, 14, 35, 20});


        System.out.println();
        System.out.println("=========================================");
        System.out.println(" findSecondBiggest coding exercise");
        System.out.println("=========================================");
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {19, 9, 11, 0, 12};
        System.out.println("findSecondBiggest([1,2,3,4,5]) = " + findSecondBiggest(arr1));
        System.out.println("findSecondBiggest([19,9,11,0,12]) = " + findSecondBiggest(arr2));
    }

    public static void printListOfStudents(Student[] students) {
        Student[] sorted = Arrays.copyOf(students, students.length);
        Arrays.sort(sorted, Comparator.comparing(Student::getName));
        for (Student s : sorted) {
            System.out.println(s);
        }
    }

    public static List<Student> getListOfPlatinumAlumniStudents(Student[] students) {
        List<Student> platinumAlumni = new ArrayList<>();
        LocalDate cutoffDate = LocalDate.now().minusYears(PLATINUM_ALUMNI_MIN_YEARS);

        for (Student s : students) {
            if (s.getDateOfAdmission() != null && !s.getDateOfAdmission().isAfter(cutoffDate)) {
                platinumAlumni.add(s);
            }
        }
        return platinumAlumni;
    }

    public static void printHelloWorld(int[] numbers) {
        for (int n : numbers) {
            boolean multipleOf5 = n % 5 == 0;
            boolean multipleOf7 = n % 7 == 0;

            if (multipleOf5 && multipleOf7) {
                System.out.println(n + " -> HelloWorld");
            } else if (multipleOf5) {
                System.out.println(n + " -> Hello");
            } else if (multipleOf7) {
                System.out.println(n + " -> World");
            } else {
                System.out.println(n + " -> (not a multiple of 5 or 7)");
            }
        }
    }

    public static int findSecondBiggest(int[] numbers) {
        if (numbers == null || numbers.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements");
        }

        int biggest = Integer.MIN_VALUE;
        int secondBiggest = Integer.MIN_VALUE;

        for (int n : numbers) {
            if (n > biggest) {
                secondBiggest = biggest;
                biggest = n;
            } else if (n > secondBiggest && n != biggest) {
                secondBiggest = n;
            }
        }
        return secondBiggest;
    }
}
