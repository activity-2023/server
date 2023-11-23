package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "child_id")
public class Child extends Person {
    @Column(name = "child_school_level")
    @Enumerated(EnumType.STRING)
    private SchoolLevel schoolLevel;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    public SchoolLevel getSchoolLevel() {
        return schoolLevel;
    }

    public Child setSchoolLevel(SchoolLevel schoolLevel) {
        this.schoolLevel = schoolLevel;
        return this;
    }

    public Parent getParent() {
        return parent;
    }

    public Child setParent(Parent parent) {
        this.parent = parent;
        return this;
    }
}
