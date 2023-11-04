package be.ucll.examenpracticum.entity;

import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(
            name = "person_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private List<Person> people;
    private String name;
    private String description;
    private Integer studyPoints;

    public Course(String name, String description, Integer studyPoints) {
        this.name = name;
        this.description = description;
        this.studyPoints = studyPoints;
    }

    public Course() {} //necessary for Hibernate

    public Long getId() {
        return id;
    }

    public List<Person> getPeople() {
        return people;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStudyPoints() {
        return studyPoints;
    }

    public void setStudyPoints(Integer studyPoints) {
        this.studyPoints = studyPoints;
    }

}
