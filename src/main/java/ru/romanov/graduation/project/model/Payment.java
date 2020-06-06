package ru.romanov.graduation.project.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @Column(name = "amount")
    @JsonProperty("amount")
    private int amount;

    @Column(name = "payment_date")
    @JsonProperty("paymentDate")
    private LocalDate paymentDate;

    @ManyToOne(targetEntity = Receipt.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "r_receipt_id")
    @JsonIgnore
    private Receipt receipt;

    @Column(name = "r_receipt_id", updatable = false, insertable = false)
    @JsonProperty("receiptId")
    private Long refReceiptId;

    public Payment() {
        paymentDate = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) { this.id = id;}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Long getRefReceiptId() { return refReceiptId;}

    public void setRefReceiptId(Long refReceiptId) { this.refReceiptId = refReceiptId;}
}
