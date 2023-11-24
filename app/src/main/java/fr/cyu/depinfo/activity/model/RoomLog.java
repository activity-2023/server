package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "room_log")
public class RoomLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rl_id")
    private Integer id;

    @Column(name = "rl_timestamp")
    private Timestamp timestamp;

    @Column(name = "rl_status")
    @Convert(converter = DoorStatusConverter.class)
    private DoorStatus doorStatus;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Integer getId() {
        return id;
    }

    public RoomLog setId(Integer id) {
        this.id = id;
        return this;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public RoomLog setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public RoomLog setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public RoomLog setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public RoomLog setPerson(Person person) {
        this.person = person;
        return this;
    }
}
