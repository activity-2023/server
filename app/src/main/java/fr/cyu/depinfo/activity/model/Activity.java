package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "activity_id")
    private Integer id;

    @Column(name = "activity_name")
    private String name;

    @Column(name = "activity_description")
    private String description;

    @Column(name = "activity_min_age")
    private Integer minAge;

    @Column(name = "activity_price")
    private Float price;

    @ManyToMany(mappedBy = Staff_.ACTIVITIES)
    private Set<Staff> staff = new HashSet<>();

    @ManyToMany(mappedBy = Person_.ACTIVITIES)
    private Set<Person> people = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = Event_.ACTIVITY, orphanRemoval = true)
    private Set<Event> events = new HashSet<>();

    public Activity addStaff(Staff staff) {
        this.staff.add(staff);
        staff.getActivities().add(this);
        return this;
    }

    public Activity removeStaff(Staff staff) {
        this.staff.remove(staff);
        staff.getActivities().remove(this);
        return this;
    }

    public Activity addPerson(Person person) {
        this.people.add(person);
        person.getActivities().add(this);
        return this;
    }

    public Activity removePerson(Person person) {
        this.people.remove(person);
        person.getActivities().remove(this);
        return this;
    }

    public Activity addEvent(Event event) {
        this.events.add(event);
        event.setActivity(this);
        return this;
    }

    public Activity removeEvent(Event event) {
        this.events.remove(event);
        event.setActivity(null);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Activity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Activity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Activity setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Activity setMinAge(Integer minAge) {
        this.minAge = minAge;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public Activity setPrice(Float price) {
        this.price = price;
        return this;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public Activity setStaff(Set<Staff> staff) {
        this.staff = staff;
        return this;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Activity setPeople(Set<Person> people) {
        this.people = people;
        return this;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Activity setEvents(Set<Event> events) {
        this.events = events;
        return this;
    }
}
