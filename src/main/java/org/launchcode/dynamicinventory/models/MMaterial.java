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

    @ManyToOne
    private Supplier supplier;

    @ManyToOne
    private Location location;

    @OneToMany
    @JoinColumn(name = "matId")
    private List<Flow> flows = new ArrayList<>();

 /*   @OneToMany
    @JoinColumn(name = "mat_Id")
    private List<Supplier> supplier;*/

/*    @OneToMany
    @JoinColumn(name = "mat_Id")
    private List<Location> locations = new ArrayList<>();*/



    public MMaterial(){}


    public int getMatId() {
        return matId;
    }

    public void setMatId(int matId) {
        this.matId = matId;
    }

    public String getMatName() {
        return matName;
    }

    public void setMatName(String matName) {
        this.matName = matName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
