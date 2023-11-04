package be.ucll.examenpracticum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseControllerTest {

    @Autowired
    private WebTestClient client;

    private static class CourseBodyValue {
        public String name;
        public String description;
        public Integer studyPoints;

        public CourseBodyValue(String name, String description, Integer studyPoints) {
            this.name = name;
            this.description = description;
            this.studyPoints = studyPoints;
        }
    }

    public static class EnrollPersonInCourseBodyValue{
        public  Long personId;
    }

    public static class SessionBodyValue{
        public String startDateTime;
        public String endDateTime;
    }
    @Test
    public void validateThatACourseCanBeCreated(){
        CourseBodyValue value = new CourseBodyValue("testCourseName0","testCourseDescription0",10);

        client.post()
                .uri("/api/v1/course")
                .bodyValue(value)
                .exchange()
                .expectStatus().isCreated()
                .expectBody().json("{\"id\":2,\"name\":\"testCourseName0\",\"description\":\"testCourseDescription0\",\"studyPoints\":10}");


    }

    @Test
    public void validateEnrollPersonInCourse(){
        EnrollPersonInCourseBodyValue value = new EnrollPersonInCourseBodyValue();
        value.personId = 2L;

        client.post()
                .uri("/api/v1/course/1/person")
                .bodyValue(value)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(String.class).isEqualTo("Person (taha2 TURGUT) enrolled in course (testCourseName1).");

    }

    @Test
    public void validateThatTheVisitDateMustBeInFormatYYYYMMDD(){
        SessionBodyValue value = new SessionBodyValue();

        value.startDateTime = "20-12-1019";
        value.endDateTime = "20-12-1020";


        client.post()
                .uri("/api/v1/course/1/session")
                .bodyValue(value)
                .exchange()
                .expectStatus().isBadRequest();
    }
    @Test
    public void validateDeleteSession(){

        client.delete()
                .uri("/api/v1/course/1/session/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Session is deleted.");
    }

    @Test
    public void validateGetCoursesWithParameters(){
        client.get()
                .uri("/api/v1/course?personId=2&page=0&size5")
                .exchange()
                .expectStatus().isOk()
                .expectBody().json("[{ \"id\": 1,\"name\": \"testCourseName1\", \"description\": \"testCourseDescription1\",\"studyPoints\": 15}]");
    }

    @Test
    public void validateThatA404IsReturnedWhenNonExistingCourseIsRequested(){
        client.get()
                .uri("/api/v1/course/200")
                .exchange()
                .expectStatus().isNotFound();
    }
}
