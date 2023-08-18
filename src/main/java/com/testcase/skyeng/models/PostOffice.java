package com.testcase.skyeng.models;

import com.testcase.skyeng.models.additions.CommonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Entity
public class PostOffice extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Поле не должно быть пустым!")
    @Size(min = 1, max = 100)
    private String name;
    private int index;
    @ManyToOne
    @JoinColumn(name = "adress_id")
    private Address address;

    @Override
    public <T> void copy(T item) {
        PostOffice newItem = (PostOffice) item;
        this.name = newItem.getName();
        this.index = newItem.getIndex();
        this.address = newItem.getAddress();
    }

    public PostOffice() {
    }

    public PostOffice(Long id, String name, int index, Address address) {
        this.id = id;
        this.name = name;
        this.index = index;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        this.index = address.getIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostOffice)) return false;
        PostOffice that = (PostOffice) o;
        return index == that.index && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, index, address);
    }

    @Override
    public String toString() {
        return "PostOffice{" +
                "name='" + name + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
