package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "building_log")
public class BuildingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bl_id")
    private Integer id;

    @Column(name = "bl_timestamp")
    private Timestamp timestamp;

    @Column(name = "bl_status")
    @Convert(converter = DoorStatusConverter.class)
    private DoorStatus doorStatus;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Integer getId() {
        return id;
    }

    public BuildingLog setId(Integer id) {
        this.id = id;
        return this;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public BuildingLog setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public DoorStatus getDoorStatus() {
        return doorStatus;
    }

    public BuildingLog setDoorStatus(DoorStatus doorStatus) {
        this.doorStatus = doorStatus;
        return this;
    }

    public Building getBuilding() {
        return building;
    }

    public BuildingLog setBuilding(Building building) {
        this.building = building;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public BuildingLog setPerson(Person person) {
        this.person = person;
        return this;
    }
}
