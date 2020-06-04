package ru.romanov.graduation.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.graduation.project.model.Address;
import ru.romanov.graduation.project.repository.AddressRepository;
import ru.romanov.graduation.project.service.AddressService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> getAddressById(long id) {
        return addressRepository.findById(id);
    }

    @Override
    @Transactional
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    @Transactional
    public void removeAddressById(long id) {
        addressRepository.deleteById(id);
    }
}
