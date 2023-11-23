package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private Integer id;

    @Column(name = "building_name")
    private String name;

    private Address address;

    @Column(name = "building_nb_floors")
    private Integer nbFloors;

    @Column(name = "building_has_elevator")
    private Boolean hasElevator;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = Room_.BUILDING, orphanRemoval = true)
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id.building", orphanRemoval = true)
    private Set<BuildingLog> buildingLogs = new HashSet<>();

    public Building addRoom(Room room) {
        this.rooms.add(room);
        room.setBuilding(this);
        return this;
    }

    public Building removeRoom(Room room) {
        this.rooms.remove(room);
        room.setBuilding(null);
        return this;
    }

    public Building addBuildingLog(BuildingLog buildingLog) {
        this.buildingLogs.add(buildingLog);
        buildingLog.getId().setBuilding(this);
        return this;
    }

    public Building removeBuildingLog(BuildingLog buildingLog) {
        this.buildingLogs.remove(buildingLog);
        buildingLog.getId().setBuilding(null);
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Building setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Building setName(String name) {
        this.name = name;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Building setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Integer getNbFloors() {
        return nbFloors;
    }

    public Building setNbFloors(Integer nbFloors) {
        this.nbFloors = nbFloors;
        return this;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public Building setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
        return this;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public Building setRooms(Set<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Set<BuildingLog> getBuildingLogs() {
        return buildingLogs;
    }

    public Building setBuildingLogs(Set<BuildingLog> buildingLogs) {
        this.buildingLogs = buildingLogs;
        return this;
    }
}
