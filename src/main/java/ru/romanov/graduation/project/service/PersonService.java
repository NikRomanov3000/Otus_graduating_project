package ru.romanov.graduation.project.service;

import ru.romanov.graduation.project.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> getAllPerson();
    Optional<Person> getPeronById(long id);
    Person addPerson(Person person);
    void removePersonById(long id);
}
