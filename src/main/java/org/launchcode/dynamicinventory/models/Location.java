package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int locationId;
    @NotNull
    private String name;
    @NotNull
    private double locationStock;

    @ManyToOne
    private MMaterial material;

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

    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
