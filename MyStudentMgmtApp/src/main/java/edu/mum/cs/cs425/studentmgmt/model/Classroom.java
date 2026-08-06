package edu.mum.cs.cs425.studentmgmt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    private long classroomId;

    @Column(name = "building_name", nullable = false)
    private String buildingName;

    @Column(name = "room_number", nullable = false)
    private String roomNumber;

    @OneToMany(mappedBy = "classroom")
    private List<Student> students = new ArrayList<>();

    public Classroom() {
    }

    public Classroom(long classroomId, String buildingName, String roomNumber) {
        this.classroomId = classroomId;
        this.buildingName = buildingName;
        this.roomNumber = roomNumber;
    }

    public long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(long classroomId) {
        this.classroomId = classroomId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
