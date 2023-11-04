package be.ucll.examenpracticum;

import be.ucll.examenpracticum.entity.Person;
import be.ucll.examenpracticum.repository.CourseRepository;
import be.ucll.examenpracticum.repository.PersonRepository;
import be.ucll.examenpracticum.repository.SessionRepository;
import be.ucll.examenpracticum.service.CourseService;
import be.ucll.examenpracticum.service.PersonService;
import be.ucll.examenpracticum.service.SessionNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private PersonRepository personRepository;
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private SessionRepository sessionRepository;
    @InjectMocks
    private CourseService service;

    @Test
    public void deleteSession_givenNotExistSessionId_returnNotFoundException(){
        //given
        Mockito.when(sessionRepository.findById(1L)).thenThrow(SessionNotFoundException.class);

        //when
        assertThrows(SessionNotFoundException.class, () ->  service.deleteSession(1L,1L));

    }
}
