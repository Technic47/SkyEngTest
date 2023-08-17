package com.testcase.skyeng.models;

import com.testcase.skyeng.models.additions.CommonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Entity
public class Person extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100)
    private String firstName;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100)
    private String secondName;
    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Address address;
    @ManyToMany
    private Set<MailPackage> packages;

    @Override
    public <T> void copy(T item) {
        Person newItem = (Person) item;
        this.firstName = newItem.getFirstName();
        this.secondName = newItem.getSecondName();
        this.address = newItem.getAddress();
        this.packages = newItem.getPackages();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<MailPackage> getPackages() {
        return packages;
    }

    public void setPackages(Set<MailPackage> packages) {
        this.packages = packages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(firstName, person.firstName) && Objects.equals(secondName, person.secondName) && Objects.equals(address, person.address) && Objects.equals(packages, person.packages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, secondName, address, packages);
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                '}';
    }


}
