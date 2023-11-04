package be.ucll.examenpracticum.dto;

public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private Integer studyPoints;

    public CourseDTO(Long id, String name, String description, Integer studyPoints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.studyPoints = studyPoints;
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
}
