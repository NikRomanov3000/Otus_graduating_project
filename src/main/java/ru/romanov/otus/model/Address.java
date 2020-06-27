package ru.romanov.otus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "address")
public class Address{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    @Column(name = "id")
    private Long id;

    @Column(name = "full_address")
    @JsonProperty("fullAddress")
    private String fullAddress;

    @ManyToOne(targetEntity = Person.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "r_person_id", referencedColumnName = "id")
    @JsonIgnore
    private Person person;

    @Column(name = "r_person_id", updatable = false, insertable = false)
    @JsonProperty("personId")
    private Long refPersonId;

    @OneToMany(targetEntity = Receipt.class, fetch = FetchType.LAZY, mappedBy = "address")
    @JsonIgnore
    private List<Receipt> receiptsToAddress;

    public Address() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public Address(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public Person getPerson() { return person;}

    public void setPerson(Person person) { this.person = person; }

    public Long getRefPersonId() { return refPersonId; }

    public void setRefPersonId(Long refPersonId) { this.refPersonId = refPersonId; }

    public List<Receipt> getReceiptsToAddress() { return receiptsToAddress; }

    public void setReceiptsToAddress(List<Receipt> receiptsToAddress) { this.receiptsToAddress = receiptsToAddress; }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", fullAddress='" + fullAddress + '\'' +
                ", refPersonId=" + refPersonId +
                '}';
    }
}


