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
    public List<Receipt> getAllReceipt() {
        return receiptService.getAllReceipt();
    }

    @GetMapping({"/receipt/{receiptId}"})
    public Optional<Receipt> getReceiptById(@PathVariable(value = "receiptId") Long id) {
        return receiptService.getReceiptById(id);
    }

    @PostMapping({"/receipt"})
    public Long saveReceipt(@RequestBody Receipt receipt) throws Exception {
        Long retId = null;
        try {
            receipt.setAddress(addressService.getAddressById(receipt.getRefAddressId()).get());
            retId = receiptService.addReceipt(receipt).getId();
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return retId;
    }

    @DeleteMapping({"/receipt/{receiptId}"})
    public boolean deleteReceiptById(@PathVariable(value = "receiptId") Long id) throws Exception {
        try {
            receiptService.removeReceiptById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }
}
