package ru.romanov.graduation.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.graduation.project.model.Person;
import ru.romanov.graduation.project.repository.PersonRepository;
import ru.romanov.graduation.project.service.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<Person> getAllPerson() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> getPeronById(long id) {
        return personRepository.findById(id);
    }

    @Override
    @Transactional
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void removePersonById(long id) {
        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePerson(Person person, long personId) {
        Optional<Person> somePerson = personRepository.findById(personId);
        if (!somePerson.isEmpty()) {
            personRepository.deleteById(personId);
        }
        personRepository.save(person);
    }
}
