package be.ucll.examenpracticum.dto;

import java.time.LocalDate;

public class SessionDTO {
    private Long id;
    private LocalDate startDateTime;
    private LocalDate endDateTime;

    public SessionDTO(Long id, LocalDate startDateTime, LocalDate endDateTime) {
        this.id = id;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
