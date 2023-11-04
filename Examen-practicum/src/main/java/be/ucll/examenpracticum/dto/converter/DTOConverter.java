package be.ucll.examenpracticum.dto.converter;

import be.ucll.examenpracticum.dto.CourseDTO;
import be.ucll.examenpracticum.dto.CoursePersonSessionDTO;
import be.ucll.examenpracticum.dto.SessionDTO;
import be.ucll.examenpracticum.entity.Course;
import be.ucll.examenpracticum.entity.Session;
import be.ucll.examenpracticum.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class DTOConverter {

    public static CourseDTO convertToCourseDTO(Course course) {

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getStudyPoints()
        );
    }

    public static SessionDTO convertToSessionDTO(Session session){
        return new SessionDTO(session.getId(),session.getStartDateTime(),session.getEndDateTime());
    }

}
