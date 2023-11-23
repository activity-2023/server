package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "building_log")
public class BuildingLog {
    @EmbeddedId
    private BuildingLogId id;

    @Column(name = "bl_timestamp")
    private Timestamp timestamp;

    @Column(name = "bl_status")
    private DoorStatus status;

    public BuildingLogId getId() {
        return id;
    }

    public BuildingLog setId(BuildingLogId id) {
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

    public DoorStatus getStatus() {
        return status;
    }

    public BuildingLog setStatus(DoorStatus status) {
        this.status = status;
        return this;
    }
}
