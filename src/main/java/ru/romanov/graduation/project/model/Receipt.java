package ru.romanov.graduation.project.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    private long id;

    @Column(name = "debt_amount")
    private int debtAmount;

    @Column(name = "dispatch_dt")
    private Date dispatchDate;

    @OneToMany(targetEntity = Payment.class, cascade = CascadeType.ALL, mappedBy = "receipt")
    private List<Payment> paymentsToReceipt;

    @OneToOne(targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "r_address_id")
    private Address address;

    public long getId() {
        return id;
    }

    public int getDebtAmount() {
        return debtAmount;
    }

    public void setDebtAmount(int debtAmount) {
        this.debtAmount = debtAmount;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public List<Payment> getPaymentsToReceipt() {
        return paymentsToReceipt;
    }

    public void setPaymentsToReceipt(List<Payment> paymentsToReceipt) {
        this.paymentsToReceipt = paymentsToReceipt;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addressOfDebtor) {
        this.address = addressOfDebtor;
    }
}

