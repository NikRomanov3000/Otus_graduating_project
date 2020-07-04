package project.service;

import project.model.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayment();

    Optional<Payment> getPaymentById(long id);

    Payment addPayment(Payment payment);

    void removePaymentById(long id);
}
