package ru.romanov.graduation.project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.romanov.graduation.project.model.Payment;
import ru.romanov.graduation.project.model.Receipt;
import ru.romanov.graduation.project.service.PaymentService;
import ru.romanov.graduation.project.service.ReceiptService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PaymentController {

    private PaymentService paymentService;
    private ReceiptService receiptService;

    public PaymentController(PaymentService paymentService, ReceiptService receiptService) {
        this.paymentService = paymentService;
        this.receiptService = receiptService;
    }

    @GetMapping({"/payment"})
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayment();
    }

    @GetMapping({"/payment/{paymentId}"})
    public Optional<Payment> getPaymentById(@PathVariable(value = "paymentId") Long id) {
        return paymentService.getPaymentById(id);
    }

    @PostMapping({"/payment"})
    public Long savePayment(@RequestBody Payment payment) throws Exception {
        Long retId = null;
        try {
            if (receiptService.getReceiptById(payment.getRefReceiptId()).isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receipt with id: " + payment.getRefReceiptId() + " doesn't exist");
            } else {
                Receipt receipt = receiptService.getReceiptById(payment.getRefReceiptId()).get();
                receiptService.updateReceipt(receipt, payment.getAmount(), false);
                payment.setReceipt(receipt);
                retId = paymentService.addPayment(payment).getId();
            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return retId;
    }

    @DeleteMapping({"/payment/{paymentId}"})
    public boolean deletePaymentById(@PathVariable(value = "paymentId") Long id) throws Exception {
        try {
            Payment payment = paymentService.getPaymentById(id).get();
            receiptService.updateReceipt(payment.getReceipt(), payment.getAmount(), true);
            paymentService.removePaymentById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }
}
