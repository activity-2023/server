package fr.cyu.depinfo.activity.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class BuildingLogId implements Serializable {
    @Serial
    private static final long serialVersionUID = -2459550566881276093L;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Building getBuilding() {
        return building;
    }

    public BuildingLogId setBuilding(Building building) {
        this.building = building;
        return this;
    }

    public Person getPerson() {
        return person;
    }

    public BuildingLogId setPerson(Person person) {
        this.person = person;
        return this;
    }
}
