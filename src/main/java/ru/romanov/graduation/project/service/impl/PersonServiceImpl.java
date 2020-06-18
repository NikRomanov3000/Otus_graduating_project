package ru.romanov.graduation.project.service.impl;

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

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

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
    public Person addPerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    @Transactional
    public void removePersonById(long id) {
        personRepository.deleteById(id);
    }
}
