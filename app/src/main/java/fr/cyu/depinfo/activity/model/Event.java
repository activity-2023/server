package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @Column(name = "event_date")
    private Date date;

    @Column(name = "event_start_time")
    private Time startTime;

    @Column(name = "event_duration")
    private Time duration;

    @Column(name = "event_max_participants")
    private Integer maxParticipants;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;

    @ManyToMany(mappedBy = Staff_.EVENTS)
    private Set<Staff> staff = new HashSet<>();

    @ManyToMany(mappedBy = Person_.EVENTS)
    private Set<Person> people = new HashSet<>();

    public Event addStaff(Staff staff) {
        this.staff.add(staff);
        staff.getEvents().add(this);
        return this;
    }

    public Event removeStaff(Staff staff) {
        this.staff.remove(staff);
        staff.getEvents().remove(this);
        return this;
    }

    public Event addPerson(Person person) {
        this.people.add(person);
        person.getEvents().add(this);
        return this;
    }

    public Event removePerson(Person person) {
        this.people.remove(person);
        person.getEvents().remove(this);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Event setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public Event setDate(Date date) {
        this.date = date;
        return this;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Event setStartTime(Time startTime) {
        this.startTime = startTime;
        return this;
    }

    public Time getDuration() {
        return duration;
    }

    public Event setDuration(Time duration) {
        this.duration = duration;
        return this;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public Event setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Event setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Activity getActivity() {
        return activity;
    }

    public Event setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public Set<Staff> getStaff() {
        return staff;
    }

    public Event setStaff(Set<Staff> staff) {
        this.staff = staff;
        return this;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public Event setPeople(Set<Person> people) {
        this.people = people;
        return this;
    }
}
