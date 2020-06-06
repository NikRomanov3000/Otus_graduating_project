package ru.romanov.graduation.project.service;

import ru.romanov.graduation.project.model.Receipt;

import java.util.List;
import java.util.Optional;

public interface ReceiptService {
    List<Receipt> getAllReceipt();
    Optional<Receipt> getReceiptById(long id);
    Receipt addReceipt(Receipt receipt);
    void removeReceiptById(long id);
    void updateReceipt(Receipt receipt, int paymentSum, boolean isDeleted);
    void checkAndUpdateReceipt(Receipt receipt);

}
