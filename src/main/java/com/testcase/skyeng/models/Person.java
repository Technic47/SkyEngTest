package com.testcase.skyeng.models;

import com.testcase.skyeng.models.additions.CommonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class Person extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private Long passportNumber;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 50)
    private String firstName;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 50)
    private String secondName;
    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Address address;

    public Person() {
    }

    public Person(Long id, Long passportNumber, String firstName, String secondName, Address address) {
        this.id = id;
        this.passportNumber = passportNumber;
        this.firstName = firstName;
        this.secondName = secondName;
        this.address = address;
    }

    @Override
    public <T> void copy(T item) {
        Person newItem = (Person) item;
        this.passportNumber = newItem.getPassportNumber();
        this.firstName = newItem.getFirstName();
        this.secondName = newItem.getSecondName();
        this.address = newItem.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(secondName, person.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }
}
