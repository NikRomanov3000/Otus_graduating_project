package ru.romanov.graduation.project.model;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "passport_serial")
    private int passportSerial;

    @Column(name = "passport_number")
    private int passportNumber;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "person")
    private Set<Address> personAddresses;

    public long getId() {
        return id;
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

    public void setPassportSerial(short passportSerial) {
        this.passportSerial = passportSerial;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(short passportNumber) {
        this.passportNumber = passportNumber;
    }
}
