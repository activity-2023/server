package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "person_fname")
    private String fName;

    @Column(name = "person_lname")
    private String lName;

    @Column(name = "person_gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "person_birth_date")
    private Date birthDate;

    @Column(name = "person_access_pin_hash")
    private String accessPin;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "subscribe",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private Set<Activity> activities = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "participate",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private Set<Event> events = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = BuildingLog_.PERSON, orphanRemoval = true)
    private Set<BuildingLog> buildingLogs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = RoomLog_.PERSON, orphanRemoval = true)
    private Set<RoomLog> roomLogs = new HashSet<>();

    public Person addActivity(Activity activity) {
        this.activities.add(activity);
        activity.getPeople().add(this);
        return this;
    }

    public Person removeActivity(Activity activity) {
        this.activities.remove(activity);
        activity.getPeople().remove(this);
        return this;
    }

    public Person addEvent(Event event) {
        this.events.add(event);
        event.getPeople().add(this);
        return this;
    }

    public Person removeEvent(Event event) {
        this.events.remove(event);
        event.getPeople().remove(this);
        return this;
    }

    public Person addBuildingLog(BuildingLog buildingLog) {
        this.buildingLogs.add(buildingLog);
        buildingLog.setPerson(this);
        return this;
    }

    public Person removeBuildingLog(BuildingLog buildingLog) {
        this.buildingLogs.remove(buildingLog);
        buildingLog.setPerson(null);
        return this;
    }

    public Person addRoomLog(RoomLog roomLog) {
        this.roomLogs.add(roomLog);
        roomLog.setPerson(this);
        return this;
    }

    public Person removeRoomLog(RoomLog roomLog) {
        this.roomLogs.remove(roomLog);
        roomLog.setPerson(null);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Person setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getfName() {
        return fName;
    }

    public Person setfName(String fName) {
        this.fName = fName;
        return this;
    }

    public String getlName() {
        return lName;
    }

    public Person setlName(String lName) {
        this.lName = lName;
        return this;
    }

    public Gender getGender() {
        return gender;
    }

    public Person setGender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Person setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public String getAccessPin() {
        return accessPin;
    }

    public Person setAccessPin(String accessPin) {
        this.accessPin = accessPin;
        return this;
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public Person setActivities(Set<Activity> activities) {
        this.activities = activities;
        return this;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Person setEvents(Set<Event> events) {
        this.events = events;
        return this;
    }

    public Set<BuildingLog> getBuildingLogs() {
        return buildingLogs;
    }

    public Person setBuildingLogs(Set<BuildingLog> buildingLogs) {
        this.buildingLogs = buildingLogs;
        return this;
    }

    public Set<RoomLog> getRoomLogs() {
        return roomLogs;
    }

    public Person setRoomLogs(Set<RoomLog> roomLogs) {
        this.roomLogs = roomLogs;
        return this;
    }
}
