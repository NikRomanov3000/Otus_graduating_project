package ru.romanov.otus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;

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

    @Column(name = "r_receipt_id", updatable = false)
    @JsonProperty(required = true, value = "receiptId" )
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

    public Long getRefReceiptId() { return refReceiptId;}

    public void setRefReceiptId(Long refReceiptId) { this.refReceiptId = refReceiptId;}
}
