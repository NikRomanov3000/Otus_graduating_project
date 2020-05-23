package ru.romanov.graduation.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.romanov.graduation.project.model.Payment;
import ru.romanov.graduation.project.repository.PaymentRepository;
import ru.romanov.graduation.project.service.PaymentService;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(long id) {
        return paymentRepository.findById(id);
    }

    @Override
    @Transactional
    public void addPayment(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public void removePaymentById(long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updatePayment(Payment payment, long paymentId) {
        Optional<Payment> somePayment = paymentRepository.findById(paymentId);
        if (!somePayment.isEmpty()) {
            paymentRepository.deleteById(paymentId);
        }
        paymentRepository.save(payment);
    }
}
