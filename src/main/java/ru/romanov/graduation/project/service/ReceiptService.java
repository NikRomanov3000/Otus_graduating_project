package ru.romanov.graduation.project.service;

import ru.romanov.otus.model.PaymentInfo;
import ru.romanov.otus.model.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    List<Receipt> getAllReceipt();
    Optional<Receipt> getReceiptById(long id);
    Receipt addReceipt(Receipt receipt);
    void removeReceiptById(long id);
    void updateReceipt(PaymentInfo paymentInfo) throws Exception;
    void checkAndUpdateReceipt(Receipt receipt);

}
