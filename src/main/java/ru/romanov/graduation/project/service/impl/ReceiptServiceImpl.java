package ru.romanov.graduation.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.romanov.graduation.project.model.Receipt;
import ru.romanov.graduation.project.repository.ReceiptRepository;
import ru.romanov.graduation.project.service.ReceiptService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptRepository receiptRepository;

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
    public void addReceipt(Receipt receipt) {
        receiptRepository.save(receipt);
    }

    @Override
    @Transactional
    public void removeReceiptById(long id) {
        receiptRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateReceipt(Receipt receipt, long receiptId) {
        Optional<Receipt> someReceipt = receiptRepository.findById(receiptId);
        if (!someReceipt.isEmpty()) {
            receiptRepository.deleteById(receiptId);
        }
        receiptRepository.save(receipt);
    }
}
