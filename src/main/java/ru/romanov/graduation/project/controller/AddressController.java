package ru.romanov.graduation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.model.Address;
import ru.romanov.graduation.project.service.AddressService;
import ru.romanov.graduation.project.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private PersonService personService;

    @GetMapping({"/address"})
    public @ResponseBody
    List<Address> getAllAddress() {
        return addressService.getAllAddress();
    }

    @GetMapping({"/address/{addressId}"})
    public @ResponseBody
    Optional<Address> getAddressById(@PathVariable(value = "addressId") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping({"/address"})
    public @ResponseBody
    boolean savePerson(@RequestBody Address address) throws Exception {
        try {
           address.setPerson(personService.getPeronById(address.getRefPersonId()).get());
           addressService.addAddress(address);
        } catch (Exception ex) {
            throw new Exception(ex);

        }
        return true;
    }

    @DeleteMapping({"/address/{addressId}"})
    public @ResponseBody
    boolean deleteAddressById(@PathVariable(value = "addressId") Long id) {
        try {
            addressService.removeAddressById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
