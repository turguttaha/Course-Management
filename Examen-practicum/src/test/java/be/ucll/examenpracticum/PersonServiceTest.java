package be.ucll.examenpracticum;

import be.ucll.examenpracticum.entity.Person;
import be.ucll.examenpracticum.repository.PersonRepository;
import be.ucll.examenpracticum.service.PersonService;
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
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;
    @InjectMocks
    private PersonService service;

    @Test
    public void getPerson_givePersonId_returnPersonObject(){
        //given
        Person person = new Person("Taha","TURGUT","taha@taha","0493000000");
        person.setId(1L);
        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        //when
        Person resPerson = service.getPerson(1L);

        // then
        assertNotNull(resPerson);
        assertEquals(person.getFirstName(),resPerson.getFirstName());
        assertEquals(person.getLastName(), resPerson.getLastName());
        assertEquals(person.getEmail(), resPerson.getEmail());
        assertEquals(person.getPhone(),resPerson.getPhone());

    }
}
