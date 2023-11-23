package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Integer id;

    @Column(name = "room_name")
    private String name;

    @Column(name = "room_floor")
    private Integer floor;

    @Column(name = "room_number")
    private Integer number;

    @Column(name = "room_type")
    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(name = "room_capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = Event_.ROOM, orphanRemoval = true)
    private Set<Event> events = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = RoomLogId_.ROOM, orphanRemoval = true)
    private Set<RoomLog> roomLogs = new HashSet<>();

    public Room addEvent(Event event) {
        this.events.add(event);
        event.setRoom(this);
        return this;
    }

    public Room removeEvent(Event event) {
        this.events.remove(event);
        event.setRoom(null);
        return this;
    }

    public Room addRoomLog(RoomLog roomLog) {
        this.roomLogs.add(roomLog);
        roomLog.getId().setRoom(this);
        return this;
    }

    public Room removeRoomLog(RoomLog roomLog) {
        this.roomLogs.remove(roomLog);
        roomLog.getId().setRoom(null);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Room setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getFloor() {
        return floor;
    }

    public Room setFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public Room setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public RoomType getType() {
        return type;
    }

    public Room setType(RoomType type) {
        this.type = type;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Room setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public Building getBuilding() {
        return building;
    }

    public Room setBuilding(Building building) {
        this.building = building;
        return this;
    }

    public Set<Event> getEvents() {
        return events;
    }

    public Room setEvents(Set<Event> events) {
        this.events = events;
        return this;
    }

    public Set<RoomLog> getRoomLogs() {
        return roomLogs;
    }

    public Room setRoomLogs(Set<RoomLog> roomLogs) {
        this.roomLogs = roomLogs;
        return this;
    }
}
