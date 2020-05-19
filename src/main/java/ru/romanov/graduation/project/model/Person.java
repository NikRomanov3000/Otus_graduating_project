package ru.romanov.graduation.project.model;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "passport_serial")
    private short passportSerial;

    @Column(name = "passport_number")
    private short passportNumber;

    public long getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public short getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(short passportSerial) {
        this.passportSerial = passportSerial;
    }

    public short getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(short passportNumber) {
        this.passportNumber = passportNumber;
    }
}
