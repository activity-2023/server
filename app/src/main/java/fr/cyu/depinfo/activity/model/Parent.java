package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "parent_id")
public class Parent extends UserAccount {
    @Column(name = "parent_email")
    private String email;

    @Column(name = "parent_phone")
    private String phone;

    @Column(name = "parent_job")
    private String job;

    private Address address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = Child_.PARENT, orphanRemoval = true)
    private Set<Child> children = new HashSet<>();

    public Parent addChild(Child child) {
        this.children.add(child);
        child.setParent(this);
        return this;
    }

    public Parent removeChild(Child child) {
        this.children.remove(child);
        child.setParent(null);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Parent setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Parent setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getJob() {
        return job;
    }

    public Parent setJob(String job) {
        this.job = job;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Parent setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Set<Child> getChildren() {
        return children;
    }

    public Parent setChildren(Set<Child> children) {
        this.children = children;
        return this;
    }
}
