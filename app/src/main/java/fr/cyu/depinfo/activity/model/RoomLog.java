package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "room_log")
public class RoomLog {
    @EmbeddedId
    private RoomLogId id;

    @Column(name = "rl_timestamp")
    private Timestamp timestamp;

    @Column(name = "rl_status")
    @Convert(converter = DoorStatusConverter.class)
    private DoorStatus doorStatus;

    public RoomLogId getId() {
        return id;
    }

    public RoomLog setId(RoomLogId id) {
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
}
