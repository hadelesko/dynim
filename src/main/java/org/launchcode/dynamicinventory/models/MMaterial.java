package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MMaterial {
    @Id
    @GeneratedValue
    private int matId;

    private String matName;

    private String description;


    private double stock;

    @OneToMany
    @JoinColumn(name = "mat_Id")
    private List<Supplier> supplier;

    @OneToMany
    @JoinColumn(name = "mat_Id")
    private List<Location> locations = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "mat_Id")
    private List<Flow> flows = new ArrayList<>();


    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }



    public List<Supplier> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<Supplier> supplier) {
        this.supplier = supplier;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
