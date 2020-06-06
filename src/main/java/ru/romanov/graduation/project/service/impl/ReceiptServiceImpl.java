package ru.romanov.graduation.project.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.romanov.graduation.project.model.Receipt;
import ru.romanov.graduation.project.model.enems.ReceiptStatuses;
import ru.romanov.graduation.project.repository.ReceiptRepository;
import ru.romanov.graduation.project.service.ReceiptService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;

    public ReceiptServiceImpl(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public List<Receipt> getAllReceipt() {
        return receiptRepository.findAll();
    }

    @Override
    public Optional<Receipt> getReceiptById(long id) {
        return receiptRepository.findById(id);
    }

    @Override
    @Transactional
    public Receipt addReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    @Override
    @Transactional
    public void removeReceiptById(long id) {
        receiptRepository.deleteById(id);
    }

    @Override
    public void updateReceipt(Receipt receipt, int paymentSum, boolean isDeleted) {
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
        addReceipt(receipt);
    }

    @Override
    public void checkAndUpdateReceipt(Receipt receipt) {
        if (receipt.getActiveAmount() < receipt.getDebtAmount() && receipt.getActiveAmount() > 0) {
            receipt.setReceiptStatus(ReceiptStatuses.partPaid.getReceiptStatusValue());
        }
        if (receipt.getActiveAmount() <= 0) {
            receipt.setReceiptStatus(ReceiptStatuses.fullPaid.getReceiptStatusValue());
        }
        if (receipt.getActiveAmount() >= receipt.getDebtAmount()) {
            receipt.setReceiptStatus(ReceiptStatuses.notPaid.getReceiptStatusValue());
        }
    }
}
