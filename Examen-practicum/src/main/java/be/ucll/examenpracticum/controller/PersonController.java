package be.ucll.examenpracticum.controller;

import be.ucll.examenpracticum.dto.PersonDTO;
import be.ucll.examenpracticum.entity.Person;
import be.ucll.examenpracticum.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person")
public class PersonController {
    private PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO){
          return ResponseEntity.status(HttpStatus.CREATED).body(personService.addPerson(personDTO));
    }

    @PutMapping("{id}")
    public ResponseEntity<Person> editPerson(@PathVariable Long id, @RequestBody PersonDTO personDTO){
        //return personService.editPerson(id,personDTO);
        return ResponseEntity.status(HttpStatus.OK).body(personService.editPerson(id,personDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Long id){
        //return personService.getPerson(id);
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPerson(id));
    }
    @GetMapping
    public ResponseEntity<List<Person>> getPeople(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                  @RequestParam(value = "size", required = false,defaultValue = "10") int size){
        //return personService.getPeoplePageable(page,size);
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPeoplePageable(page,size));
    }

}
