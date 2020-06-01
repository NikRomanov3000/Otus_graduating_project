package ru.romanov.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @JsonProperty("id")
    @Column(name = "id")
    private long id;

    @Column(name = "debt_amount")
    @JsonProperty("debtAmount")
    private int debtAmount;

    @Column(name = "dispatch_dt")
    @JsonProperty("dispatchDate")
    private Date dispatchDate;

    /**
     * @author Romanov Nikita (boss of this project)
     * 1 - не оплачено (платёж не поступал)
     * 2 - часьтчено оплачено
     * 3 - оплачена полностью
     */

    @Column(name = "receipt_status")
    @JsonProperty("receiptStatus")
    private int receiptStatus;

    @Column(name = "active_amount")
    @JsonProperty("activeAmount")
    private int activeAmount;


    @OneToMany(targetEntity = Payment.class, fetch = FetchType.LAZY, mappedBy = "receipt")
    @JsonIgnore
    private List<Payment> paymentsToReceipt;

    @ManyToOne(targetEntity = Address.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "r_address_id")
    @JsonIgnore
    private Address address;

    @Column(name = "r_address_id", updatable = false, insertable = false)
    @JsonProperty("addressId")
    private Long refAddressId;

    public Receipt() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public int getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(int receiptStatus) {
        this.receiptStatus = receiptStatus;
    }

    public int getActiveAmount() {
        return activeAmount;
    }

    public void setActiveAmount(int activeAmount) {
        this.activeAmount = activeAmount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address addressOfDebtor) {
        this.address = addressOfDebtor;
    }

    public Long getRefAddressId() {
        return refAddressId;
    }

    public void setRefAddressId(Long refAddressId) {
        this.refAddressId = refAddressId;
    }

}

