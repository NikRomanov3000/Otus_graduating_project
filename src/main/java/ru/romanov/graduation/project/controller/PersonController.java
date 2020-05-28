package ru.romanov.graduation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.model.Person;
import ru.romanov.graduation.project.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Person> getAllPerson() {
        return personService.getAllPerson();
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Optional<Person> getPersonById(@PathVariable(value = "personId") Long id) {
        return personService.getPeronById(id);
    }


    @RequestMapping(value = "/person", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    boolean savePerson(@RequestBody Person person) {
        try {
            personService.addPerson(person);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/person/{personId}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    boolean deletePersonById(@PathVariable(value = "personId") Long id) {
        try {
            personService.removePersonById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
