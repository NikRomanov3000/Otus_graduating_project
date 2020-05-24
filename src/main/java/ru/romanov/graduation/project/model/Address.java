package ru.romanov.graduation.project.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "full_address")
    private String fullAddress;

    @ManyToOne(targetEntity = Person.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "r_person_id", nullable = false)
    private Person person;

    @OneToOne(mappedBy = "address")
    private Receipt receiptToAddress;

    public long getId() {
        return id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Receipt getReceiptToAddress() {
        return receiptToAddress;
    }

    public void setReceiptToAddress(Receipt receiptToAddress) {
        this.receiptToAddress = receiptToAddress;
    }
}
