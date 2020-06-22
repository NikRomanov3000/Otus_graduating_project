package ru.romanov.graduation.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.otus.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
