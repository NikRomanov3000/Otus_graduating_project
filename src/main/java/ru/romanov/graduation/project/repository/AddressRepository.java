package ru.romanov.graduation.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romanov.graduation.project.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
