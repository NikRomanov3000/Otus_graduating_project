package ru.romanov.graduation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.model.Receipt;
import ru.romanov.graduation.project.service.AddressService;
import ru.romanov.graduation.project.service.ReceiptService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReceiptController {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private AddressService addressService;


    @GetMapping({"/receipt"})
    public @ResponseBody
    List<Receipt> getAllAddress() {
        return receiptService.getAllReceipt();
    }

    @GetMapping({"/receipt/{receiptId}"})
    public @ResponseBody
    Optional<Receipt> getAddressById(@PathVariable(value = "receiptId") Long id) {
        return receiptService.getReceiptById(id);
    }

    @PostMapping({"/receipt"})
    public @ResponseBody
    boolean savePerson(@RequestBody Receipt receipt) throws Exception {
        try {
            receipt.setAddress(addressService.getAddressById(receipt.getRefAddressId()).get());
            receiptService.addReceipt(receipt);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }

    @DeleteMapping({"/receipt/{receiptId}"})
    public @ResponseBody
    boolean deleteAddressById(@PathVariable(value = "receiptId") Long id) throws Exception {
        try {
            receiptService.removeReceiptById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }
}
