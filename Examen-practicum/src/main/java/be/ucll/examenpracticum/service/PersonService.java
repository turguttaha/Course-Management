package be.ucll.examenpracticum.service;

import be.ucll.examenpracticum.dto.PersonDTO;
import be.ucll.examenpracticum.entity.Person;
import be.ucll.examenpracticum.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.List;

@Service
@Transactional
public class PersonService {
    private PersonRepository personRepository;
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person addPerson(PersonDTO personDTO){
        return personRepository.save(new Person(
                personDTO.getFirstName(),
                personDTO.getLastName(),
                personDTO.getEmail(),
                personDTO.getPhone()
        ));
    }

    public Person getPerson(Long id){
       return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public Person editPerson(Long id, PersonDTO personDTO){
        Person foundPerson = getPerson(id);
        foundPerson.setFirstName(personDTO.getFirstName());
        foundPerson.setLastName(personDTO.getLastName());
        foundPerson.setPhone(personDTO.getPhone());
        foundPerson.setEmail(personDTO.getEmail());
        return personRepository.save(foundPerson);
    }

    public List<Person> getPeoplePageable(int page, int size ){
        Pageable pageable = PageRequest.of(page,size);
        return personRepository.findAll(pageable).getContent();
    }
}
