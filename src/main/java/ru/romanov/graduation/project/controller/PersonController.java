package ru.romanov.graduation.project.controller;

import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.service.PersonService;
import ru.romanov.otus.model.Person;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping({"/person"})
    public @ResponseBody
    List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping({"/person/{personId}"})
    public Optional<Person> getPersonById(@PathVariable(value = "personId") Long id) {
        return personService.getPeronById(id);
    }


    @PostMapping({"/person"})
    public Long savePerson(@RequestBody Person person) throws Exception {
        Long retId = null;
        try {
             retId =  personService.addPerson(person).getId();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return retId;
    }

    @DeleteMapping({"/person/{personId}"})
    public boolean deletePersonById(@PathVariable(value = "personId") Long id) {
        try {
            personService.removePersonById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
