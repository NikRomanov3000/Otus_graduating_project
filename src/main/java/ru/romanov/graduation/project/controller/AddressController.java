package ru.romanov.graduation.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.romanov.graduation.project.service.AddressService;
import ru.romanov.graduation.project.service.PersonService;
import ru.romanov.otus.model.Address;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    private final AddressService addressService;
    private final PersonService personService;

    public AddressController(AddressService addressService, PersonService personService) {
        this.addressService = addressService;
        this.personService = personService;
    }

    @GetMapping({"/address"})
    public List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping({"/address/{addressId}"})
    public Optional<Address> getAddressById(@PathVariable(value = "addressId") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping({"/address"})
    public Long saveAddress(@RequestBody Address address) throws Exception {
        Long retId = null;
        try {
            if(personService.getPeronById(address.getRefPersonId()).isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Person with id: "+address.getRefPersonId()+" doesn't exist");
            }else {
                address.setPerson(personService.getPeronById(address.getRefPersonId()).get());
                retId = addressService.addAddress(address).getId();
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return retId;
    }

    @DeleteMapping({"/address/{addressId}"})
    public boolean deleteAddressById(@PathVariable(value = "addressId") Long id) {
        try {
            addressService.removeAddressById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
