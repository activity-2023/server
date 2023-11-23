package fr.cyu.depinfo.activity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "external_staff")
@PrimaryKeyJoinColumn(name = "ex_staff_id")
public class ExternalStaff extends Staff {
    @Column(name = "ex_staff_origin")
    private String origin;

    @Column(name = "ex_staff_job")
    private String job;

    public String getOrigin() {
        return origin;
    }

    public ExternalStaff setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public String getJob() {
        return job;
    }

    public ExternalStaff setJob(String job) {
        this.job = job;
        return this;
    }
}
