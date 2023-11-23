package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "staff_presence")
public class StaffPresence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_pres_id")
    private Integer id;

    @Column(name = "staff_pres_date")
    private Date date;

    @Column(name = "staff_pres_start_time")
    private Time startTime;

    @Column(name = "staff_pres_duration")
    private Time duration;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    public Integer getId() {
        return id;
    }

    public StaffPresence setId(Integer id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public StaffPresence setDate(Date date) {
        this.date = date;
        return this;
    }

    public Time getStartTime() {
        return startTime;
    }

    public StaffPresence setStartTime(Time startTime) {
        this.startTime = startTime;
        return this;
    }

    public Time getDuration() {
        return duration;
    }

    public StaffPresence setDuration(Time duration) {
        this.duration = duration;
        return this;
    }

    public Staff getStaff() {
        return staff;
    }

    public StaffPresence setStaff(Staff staff) {
        this.staff = staff;
        return this;
    }
}
