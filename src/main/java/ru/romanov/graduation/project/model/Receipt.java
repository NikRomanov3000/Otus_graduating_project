package ru.romanov.graduation.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="receipt")
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "debt_amount")
    private int debtAmount;

    @Column(name = "dispatch_dt")
    private Date dispatchDate;

    @Column(name="r_person_id")
    private Person person;

    @Column(name="r_address_id")
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
