package ru.romanov.graduation.project.service;
import ru.romanov.graduation.project.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {
    List<Address> getAllAddress();
    Optional<Address> getAddressById(long id);
    void addAddress(Address address);
    void removeAddressById(long id);
}
