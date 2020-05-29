package ru.romanov.graduation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.model.Payment;
import ru.romanov.graduation.project.service.PaymentService;
import ru.romanov.graduation.project.service.ReceiptService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ReceiptService receiptService;

    @GetMapping({"/payment"})
    public @ResponseBody
    List<Payment> getAllAddress() {
        return paymentService.getAllPayment();
    }

    @GetMapping({"/payment/{paymentId}"})
    public @ResponseBody
    Optional<Payment> getAddressById(@PathVariable(value = "paymentId") Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping({"/payment"})
    public @ResponseBody
    boolean savePerson(@RequestBody Payment payment) throws Exception {
        try {
            payment.setReceipt(receiptService.getReceiptById(payment.getRefReceiptId()).get());
            paymentService.addPayment(payment);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }

    @DeleteMapping({"/payment/{paymentId}"})
    public @ResponseBody
    boolean deleteAddressById(@PathVariable(value = "paymentId") Long id) throws Exception {
        try {
            receiptService.removeReceiptById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }
}
