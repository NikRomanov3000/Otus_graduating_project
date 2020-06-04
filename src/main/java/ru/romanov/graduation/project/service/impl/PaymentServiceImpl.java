package ru.romanov.graduation.project.service.impl;

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

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

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
}
