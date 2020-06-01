package ru.romanov.graduation.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.web.bind.annotation.*;
import ru.romanov.graduation.project.model.Payment;
import ru.romanov.graduation.project.model.Receipt;
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
            Receipt receipt = receiptService.getReceiptById(payment.getRefReceiptId()).get();
            payment.setReceipt(receipt);
            paymentService.addPayment(payment);
            updateReceipt(receipt, payment.getAmount(), false);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }

    @DeleteMapping({"/payment/{paymentId}"})
    public @ResponseBody
    boolean deleteAddressById(@PathVariable(value = "paymentId") Long id) throws Exception {
        try {
            Payment payment = paymentService.getPaymentById(id).get();
            updateReceipt(payment.getReceipt(), payment.getAmount(), true);
            paymentService.removePaymentById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return true;
    }

    private void updateReceipt(Receipt receipt, int paymentSum, boolean isDeleted) {
        if (!isDeleted) {
            if (paymentSum > 0) {
                if (receipt.getActiveAmount() > 0) {
                    receipt.setActiveAmount(receipt.getActiveAmount() - paymentSum);
                }
            }
        } else {
            receipt.setActiveAmount(receipt.getActiveAmount() + paymentSum);
        }
        checkAndUpdateReceipt(receipt);

        receiptService.addReceipt(receipt);
    }

    private void checkAndUpdateReceipt(Receipt receipt) {
        if (receipt.getActiveAmount() < receipt.getDebtAmount() && receipt.getActiveAmount() > 0) {
            receipt.setReceiptStatus(2);
        }
        if (receipt.getActiveAmount() <= 0) {
            receipt.setReceiptStatus(3);
        }
        if(receipt.getActiveAmount()>=receipt.getDebtAmount())
        {
            receipt.setReceiptStatus(1);
        }
    }
}
