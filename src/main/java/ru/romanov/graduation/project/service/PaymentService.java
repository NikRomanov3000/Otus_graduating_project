package ru.romanov.graduation.project.service;

import ru.romanov.graduation.project.model.Payment;


import java.util.List;
import java.util.Optional;

public interface PaymentService {
    List<Payment> getAllPayment();
    Optional<Payment> getPaymentById(long id);
    void addPayment(Payment payment);
    void removePaymentById(long id);
    void updatePayment(Payment payment, long paymentId);

}
