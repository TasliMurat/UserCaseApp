package com.proje.konutapp.entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@SequenceGenerator(name="seq", initialValue=1, allocationSize=1)
@Table(name = "apartment")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="seq")
    private Integer id;
    private String adName;
    private String address;
    private Integer area;
    private Integer ageOfApartment;
    private String apartmentName;
    private Integer numberOfBedrooms;
    private Integer numberOfBathrooms;
    private Integer floorLevel;
    private Boolean presenceOfBalcony;
    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getAgeOfApartment() {
        return ageOfApartment;
    }

    public void setAgeOfApartment(Integer ageOfApartment) {
        this.ageOfApartment = ageOfApartment;
    }

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public Integer getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(Integer numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public Integer getFloorLevel() {
        return floorLevel;
    }

    public void setFloorLevel(Integer floorLevel) {
        this.floorLevel = floorLevel;
    }

    public Boolean getPresenceOfBalcony() {
        return presenceOfBalcony;
    }

    public void setPresenceOfBalcony(Boolean presenceOfBalcony) {
        this.presenceOfBalcony = presenceOfBalcony;
    }

}
