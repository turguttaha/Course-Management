package be.ucll.examenpracticum.dto;

import be.ucll.examenpracticum.entity.Person;
import be.ucll.examenpracticum.entity.Session;

import java.util.List;

public class CoursePersonSessionDTO {
    private Long id;
    private String name;
    private String description;
    private Integer studyPoints;
    private List<Person> people;
    private List<SessionDTO> sessions;

    public CoursePersonSessionDTO(Long id, String name, String description, Integer studyPoints, List<Person> people, List<SessionDTO> sessions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.studyPoints = studyPoints;
        this.people = people;
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<SessionDTO> getSessions() {
        return sessions;
    }

    public void setSessions(List<SessionDTO> sessions) {
        this.sessions = sessions;
    }
}
