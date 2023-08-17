package com.testcase.skyeng.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class MailPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private PackageType type;
    private int receiverIndex;
    @ManyToOne
    @JoinColumn(name = "receiverAddr_id")
    private Address receiverAddress;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Person receiver;

    public MailPackage(){}

    public MailPackage(PackageType type, int receiverIndex, Address receiverAddress, Person receiver) {
        this.type = type;
        this.receiverIndex = receiverIndex;
        this.receiverAddress = receiverAddress;
        this.receiver = receiver;
    }

    public MailPackage(PackageType type, Address receiverAddress, Person receiver) {
        this(type, receiverAddress.getIndex(), receiverAddress, receiver);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PackageType getType() {
        return type;
    }

    public void setType(PackageType type) {
        this.type = type;
    }

    public int getReceiverIndex() {
        return receiverIndex;
    }

    public void setReceiverIndex(int receiverIndex) {
        this.receiverIndex = receiverIndex;
    }

    public Address getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(Address receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MailPackage)) return false;
        MailPackage that = (MailPackage) o;
        return receiverIndex == that.receiverIndex && Objects.equals(id, that.id) && type == that.type && Objects.equals(receiverAddress, that.receiverAddress) && Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, receiverIndex, receiverAddress, receiver);
    }

    @Override
    public String toString() {
        return "MailPackage{" +
                "type=" + type +
                ", receiverAddress=" + receiverAddress.toString() +
                ", receiver=" + receiver.toString() +
                '}';
    }
}
