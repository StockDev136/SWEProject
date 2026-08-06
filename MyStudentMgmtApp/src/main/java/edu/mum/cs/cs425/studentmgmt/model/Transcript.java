package edu.mum.cs.cs425.studentmgmt.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transcript")
public class Transcript {

    @Id
    private long transcriptId;

    @Column(name = "degree_title", nullable = false)
    private String degreeTitle;

    @OneToOne
    @JoinColumn(name = "student_id", unique = true)
    private Student student;

    public Transcript() {
    }

    public Transcript(long transcriptId, String degreeTitle) {
        this.transcriptId = transcriptId;
        this.degreeTitle = degreeTitle;
    }

    public long getTranscriptId() {
        return transcriptId;
    }

    public void setTranscriptId(long transcriptId) {
        this.transcriptId = transcriptId;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        this.degreeTitle = degreeTitle;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
