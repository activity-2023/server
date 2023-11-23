package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "staff_id")
public class Staff extends User {
    @Column(name = "staff_email")
    private String email;

    @Column(name = "staff_phone")
    private String phone;

    @Column(name = "staff_contract_type")
    @Enumerated(EnumType.STRING)
    private ContractType contractType;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "propose",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "organize",
            joinColumns = @JoinColumn(name = "staff_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = StaffPresence_.STAFF, orphanRemoval = true)
    private Set<StaffPresence> staffPresences = new HashSet<>();

    public Staff addActivity(Activity activity) {
        this.activities.add(activity);
        activity.getStaff().add(this);
        return this;
    }

    public Staff removeActivity(Activity activity) {
        this.activities.remove(activity);
        activity.getStaff().remove(this);
        return this;
    }

    public Staff addEvent(Event event) {
        this.events.add(event);
        event.getStaff().add(this);
        return this;
    }

    public Staff removeEvent(Event event) {
        this.events.remove(event);
        event.getStaff().remove(this);
        return this;
    }

    public Staff addStaffPresence(StaffPresence staffPresence) {
        this.staffPresences.add(staffPresence);
        staffPresence.setStaff(this);
        return this;
    }

    public Staff removeStaffPresence(StaffPresence staffPresence) {
        this.staffPresences.remove(staffPresence);
        staffPresence.setStaff(null);
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Staff setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Staff setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public Staff setContractType(ContractType contractType) {
        this.contractType = contractType;
        return this;
    }

    @Override
    public Set<Activity> getActivities() {
        return activities;
    }

    @Override
    public Staff setActivities(Set<Activity> activities) {
        this.activities = activities;
        return this;
    }

    @Override
    public Set<Event> getEvents() {
        return events;
    }

    @Override
    public Staff setEvents(Set<Event> events) {
        this.events = events;
        return this;
    }

    public Set<StaffPresence> getStaffPresences() {
        return staffPresences;
    }

    public Staff setStaffPresences(Set<StaffPresence> staffPresences) {
        this.staffPresences = staffPresences;
        return this;
    }
}
