package ru.romanov.graduation.project.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.server.ResponseStatusException;
import ru.romanov.graduation.project.repository.ReceiptRepository;
import ru.romanov.graduation.project.service.ReceiptService;
import ru.romanov.otus.model.PaymentInfo;
import ru.romanov.otus.model.Receipt;
import ru.romanov.otus.model.enums.ReceiptStatuses;

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
    @Transactional
    public void updateReceipt(PaymentInfo paymentInfo) throws Exception {
       Optional<Receipt> receiptOptional = receiptRepository.findById(paymentInfo.getReceiptId());
        Receipt receiptForUpdate;
        try{
            if(receiptOptional.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Receipt with id: "+paymentInfo.getReceiptId()+" doesn't exist");
            } else {
                receiptForUpdate = receiptOptional.get();
            }
        } catch (Exception ex){
            throw new Exception(ex);
        }

        if (!paymentInfo.isDeleted()) {
            if (paymentInfo.getAmount() > 0) {
                if (receiptForUpdate.getActiveAmount() > 0) {
                    receiptForUpdate.setActiveAmount(receiptForUpdate.getActiveAmount() - paymentInfo.getAmount());
                }
            }
        } else {
            receiptForUpdate.setActiveAmount(receiptForUpdate.getActiveAmount() + paymentInfo.getAmount());
        }

        checkAndUpdateReceipt(receiptForUpdate);
        addReceipt(receiptForUpdate);
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
