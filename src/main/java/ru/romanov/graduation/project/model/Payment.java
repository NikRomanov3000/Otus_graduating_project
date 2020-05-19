package ru.romanov.graduation.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;

    @Column(name = "amount")
    int amount;

    @Column(name = "payment_date")
    Date paymentDate;

    @ManyToOne(targetEntity = Receipt.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "r_receipt_id")
    Receipt receipt;

    public long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
