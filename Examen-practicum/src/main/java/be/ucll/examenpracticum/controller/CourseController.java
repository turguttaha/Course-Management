package be.ucll.examenpracticum.controller;

import be.ucll.examenpracticum.dto.CourseDTO;
import be.ucll.examenpracticum.dto.CoursePersonSessionDTO;
import be.ucll.examenpracticum.dto.EnrollPersonInCourseDTO;
import be.ucll.examenpracticum.dto.SessionDTO;
import be.ucll.examenpracticum.dto.converter.DTOConverter;
import be.ucll.examenpracticum.entity.Course;
import be.ucll.examenpracticum.entity.Session;
import be.ucll.examenpracticum.service.CourseService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CourseDTO courseDTO){
        //return courseService.addCourse(courseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(courseDTO));
    }

    @PostMapping("{id}/person")
    public ResponseEntity<String> enrollPersonInCourse(@PathVariable Long id, @RequestBody EnrollPersonInCourseDTO enrollPersonInCourseDTO){
        return courseService.enrollPersonInCourse(id, enrollPersonInCourseDTO.getPersonId());
    }

    @PostMapping("{id}/session")
    public ResponseEntity<String> createSession(@PathVariable Long id, @RequestBody SessionDTO sessionDTO){
        return courseService.addSessionToCourse(id,sessionDTO);
    }
    @DeleteMapping("{course_id}/session/{session_id}")
    public ResponseEntity<String> deleteSession(@PathVariable Long course_id,@PathVariable Long session_id){
        return courseService.deleteSession(course_id,session_id);
    }
    @GetMapping
    public List<CourseDTO> getCourses(@RequestParam(value = "personId", required = false) Long personId,
                                   @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                   @RequestParam(value = "size", required = false,defaultValue = "10") int size){
        return courseService.getCoursesPageableById(personId,page,size).stream()
                .map(x-> DTOConverter.convertToCourseDTO(x))
                .collect(Collectors.toList());
    }
    @GetMapping("{id}")
    public ResponseEntity<CoursePersonSessionDTO> getCourse(@PathVariable Long id){
        //return courseService.getCourse(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseService.getCourse(id));
    }
}
