package fr.cyu.depinfo.activity.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class RoomLogId implements Serializable {
    @Serial
    private static final long serialVersionUID = 5863504348632215966L;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Room getRoom() {
        return room;
    }

    public RoomLogId setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public RoomLogId setPerson(Person person) {
        this.person = person;
        return this;
    }
}
