package fr.cyu.depinfo.activity.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    @Column(name = "address_street_number")
    private Integer streetNumber;

    @Column(name = "address_street_name")
    private String streetName;

    @Column(name = "address_zip_code")
    private String zipCode;

    @Column(name = "address_city")
    private String city;

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public Address setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
        return this;
    }

    public String getStreetName() {
        return streetName;
    }

    public Address setStreetName(String streetName) {
        this.streetName = streetName;
        return this;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Address setZipCode(String zipCode) {
        this.zipCode = zipCode;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Address setCity(String city) {
        this.city = city;
        return this;
    }
}
