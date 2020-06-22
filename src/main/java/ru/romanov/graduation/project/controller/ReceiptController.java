package ru.romanov.graduation.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.romanov.graduation.project.service.AddressService;
import ru.romanov.graduation.project.service.ReceiptService;
import ru.romanov.otus.model.PaymentInfo;
import ru.romanov.otus.model.Receipt;

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
            if(addressService.getAddressById(receipt.getRefAddressId()).isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Address with id: "+receipt.getRefAddressId()+" doesn't exist");
            } else {
                receipt.setAddress(addressService.getAddressById(receipt.getRefAddressId()).get());
                retId = receiptService.addReceipt(receipt).getId();
            }
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

    @PutMapping({"/receipt/{receiptId}"})
    public boolean updateReceiptAfterReceivingPayment(@PathVariable(value = "receiptId") Long id, @RequestBody PaymentInfo paymentInfo) throws Exception {
        try {
            receiptService.updateReceipt(paymentInfo);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }
}
