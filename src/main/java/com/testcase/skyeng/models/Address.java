package com.testcase.skyeng.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100)
    private String country;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 50)
    private String city;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 150)
    private String addressLine1;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 150)
    private String addressLine2;
    @NotBlank(message = "Поле не должно быть пустым!")
    private int index;
    @OneToMany
    private Set<Person> persons;
    @OneToMany
    private Set<Person> postOffices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Person> getPostOffices() {
        return postOffices;
    }

    public void setPostOffices(Set<Person> postOffices) {
        this.postOffices = postOffices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return index == address.index && Objects.equals(id, address.id) && Objects.equals(country, address.country) && Objects.equals(city, address.city) && Objects.equals(addressLine1, address.addressLine1) && Objects.equals(addressLine2, address.addressLine2) && Objects.equals(persons, address.persons) && Objects.equals(postOffices, address.postOffices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, country, city, addressLine1, addressLine2, index, persons, postOffices);
    }

    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", index=" + index +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                '}';
    }
}
