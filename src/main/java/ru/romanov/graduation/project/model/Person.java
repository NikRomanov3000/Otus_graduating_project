package ru.romanov.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "person_name")
    @JsonProperty("name")
    private String personName;

    @Column(name = "passport_serial")
    @JsonProperty("passportSerial")
    private int passportSerial;

    @Column(name = "passport_number")
   @JsonProperty("passportNumber")
    private int passportNumber;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    @JsonIgnore
    private Set<Address> personAddresses;


    public Person() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(int passportSerial) {
        this.passportSerial = passportSerial;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Set<Address> getPersonAddresses() {
        return personAddresses;
    }

    public void setPersonAddresses(Set<Address> personAddresses) {
        this.personAddresses = personAddresses;
    }

    public void addPersonAddress(Address personAddresses) {
        this.personAddresses.add(personAddresses);
    }
}
