package ru.romanov.graduation.project.controller;

import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.model.Receipt;
import ru.romanov.graduation.project.service.AddressService;
import ru.romanov.graduation.project.service.ReceiptService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final AddressService addressService;

    public ReceiptController(ReceiptService receiptService, AddressService addressService) {
        this.receiptService = receiptService;
        this.addressService = addressService;
    }

    @GetMapping({"/receipt"})
    public List<Receipt> getAllAddress() {
        return receiptService.getAllReceipt();
    }

    @GetMapping({"/receipt/{receiptId}"})
    public Optional<Receipt> getAddressById(@PathVariable(value = "receiptId") Long id) {
        return receiptService.getReceiptById(id);
    }

    @PostMapping({"/receipt"})
    public boolean savePerson(@RequestBody Receipt receipt) throws Exception {
        try {
            receipt.setAddress(addressService.getAddressById(receipt.getRefAddressId()).get());
            receiptService.addReceipt(receipt);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }

    @DeleteMapping({"/receipt/{receiptId}"})
    public boolean deleteAddressById(@PathVariable(value = "receiptId") Long id) throws Exception {
        try {
            receiptService.removeReceiptById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }
}
