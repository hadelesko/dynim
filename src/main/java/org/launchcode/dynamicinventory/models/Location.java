package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int locationId;
    private String name;
    private double locationStock;

    @OneToMany
    @JoinColumn(name="locationId")
    private List<MMaterial> materials;

    @OneToMany
    @JoinColumn(name="locationId")
    private List<Flow> flows=new ArrayList<>();

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

    public double getLocationStock() {
        return locationStock;
    }

    public void setLocationStock(double locationStock) {
        this.locationStock = locationStock;
    }

    public List<MMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MMaterial> materials) {
        this.materials = materials;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
