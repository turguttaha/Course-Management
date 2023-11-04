package be.ucll.examenpracticum.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate startDateTime;
    private LocalDate endDateTime;
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    public Session( LocalDate startDateTime, LocalDate endDateTime) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Session() {} //necessary for Hibernate

    public Long getId() {
        return id;
    }

    public LocalDate getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDate startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDate getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDate endDateTime) {
        this.endDateTime = endDateTime;
    }
}
