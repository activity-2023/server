package fr.cyu.depinfo.activity.model;

import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "internal_staff")
@PrimaryKeyJoinColumn(name = "int_staff_id")
public class InternalStaff extends Staff {
    @NaturalId
    @Column(name = "int_staff_hr_number")
    private Integer hrNumber;

    @Column(name = "int_staff_function")
    @Enumerated(EnumType.STRING)
    private StaffFunction function;
    private Address address;

    public Integer getHrNumber() {
        return hrNumber;
    }

    public InternalStaff setHrNumber(Integer hrNumber) {
        this.hrNumber = hrNumber;
        return this;
    }

    public StaffFunction getFunction() {
        return function;
    }

    public InternalStaff setFunction(StaffFunction function) {
        this.function = function;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public InternalStaff setAddress(Address address) {
        this.address = address;
        return this;
    }
}
