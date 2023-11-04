package be.ucll.examenpracticum.service;

import be.ucll.examenpracticum.dto.CourseDTO;
import be.ucll.examenpracticum.dto.CoursePersonSessionDTO;
import be.ucll.examenpracticum.dto.SessionDTO;
import be.ucll.examenpracticum.dto.converter.DTOConverter;
import be.ucll.examenpracticum.entity.Course;
import be.ucll.examenpracticum.entity.Person;
import be.ucll.examenpracticum.entity.Session;
import be.ucll.examenpracticum.repository.CourseRepository;
import be.ucll.examenpracticum.repository.PersonRepository;
import be.ucll.examenpracticum.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CourseService {
    private CourseRepository courseRepository;
    private PersonRepository personRepository;
    private SessionRepository sessionRepository;
    @Autowired
    public CourseService(CourseRepository courseRepository, PersonRepository personRepository, SessionRepository sessionRepository) {
        this.courseRepository = courseRepository;
        this.personRepository = personRepository;
        this.sessionRepository = sessionRepository;
    }

    public Course addCourse(CourseDTO courseDTO){
        return courseRepository.save(new Course(courseDTO.getName(),courseDTO.getDescription(),courseDTO.getStudyPoints()));
    }

    public ResponseEntity<String> enrollPersonInCourse(Long id,Long personId){
        Course course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new); // 404 not found
        Person person = personRepository.findById(personId).orElseThrow(PersonNotFoundException::new); //404 not found

        course.getPeople().add(person);
        courseRepository.save(course);

        return ResponseEntity.status(HttpStatus.CREATED).body("Person (%s %s) enrolled in course (%s).".formatted(person.getFirstName(),person.getLastName(),course.getName())); //  201 created
    }

    public ResponseEntity<String> addSessionToCourse(Long id, SessionDTO sessionDTO){
        Course course = courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
        Session session = new Session(sessionDTO.getStartDateTime(),sessionDTO.getEndDateTime());
        session.setCourse(course);
        sessionRepository.save(session);
        return ResponseEntity.status(HttpStatus.CREATED).body("New Session is added for %s".formatted(course.getName())); // created 201 status code
    }

    public ResponseEntity<String> deleteSession(Long course_id,Long session_id){
        //Course course = courseRepository.findById(course_id).orElseThrow(CourseNotFoundException::new);
        Session session = sessionRepository.findById(session_id).orElseThrow(SessionNotFoundException::new);
        sessionRepository.delete(session);
        return ResponseEntity.status(HttpStatus.OK).body("Session is deleted."); // OK -> HTTP Status 200
    }
    public List<Course> getCoursesPageableById(Long personId,int page, int size ){
        Pageable pageable = PageRequest.of(page,size);
        if(personId==null){
            return courseRepository.findAll(pageable).getContent();
        }

        return courseRepository.getCourseByPersonId(personId,pageable).getContent();
    }
    public CoursePersonSessionDTO getCourse(Long id){
        Course foundCourse= courseRepository.findById(id).orElseThrow(CourseNotFoundException::new);
        Sort sort = Sort.by(Sort.Order.asc("startDateTime"));
        Pageable pageable = PageRequest.of(0,10,sort);
        List<SessionDTO> sortedSessions= sessionRepository.findByCourseId(foundCourse.getId(),pageable).getContent().stream()
                .map(x-> DTOConverter.convertToSessionDTO(x))
                .collect(Collectors.toList());

        CoursePersonSessionDTO coursePersonSessionDTO =new CoursePersonSessionDTO(
                foundCourse.getId(),
                foundCourse.getName(),
                foundCourse.getDescription(),
                foundCourse.getStudyPoints(),
                foundCourse.getPeople(),
                sortedSessions);
        return coursePersonSessionDTO;
        //return ResponseEntity.status(HttpStatus.OK).body(coursePersonSessionDTO);

    }
}
