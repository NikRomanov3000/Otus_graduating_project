package ru.romanov.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate dispatchDate;

    /**
     * @author Romanov Nikita
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
        dispatchDate = LocalDate.now();
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

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(LocalDate dispatchDate) { this.dispatchDate = dispatchDate; }

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

