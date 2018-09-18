package org.launchcode.dynamicinventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Location {

    @Id
    @GeneratedValue
    private int locationId;
    private String name;
    private double locstock;

    @ManyToOne
    private MMaterial material;

    public Location(){}

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLocstock() {
        return locstock;
    }

    public void setLocstock(double locstock) {
        this.locstock = locstock;
    }

    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }
}
