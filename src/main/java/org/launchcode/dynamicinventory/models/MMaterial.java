package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    /*@ManyToOne
    private Location location;*/

    @OneToMany
    @JoinColumn(name = "materialId")
    private List<Location> locations = new ArrayList<Location>();

    @OneToMany
    @JoinColumn(name = "matId")
    private List<Flow> flows = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "matId")
    private List<MaterialOrder> materialOrders = new ArrayList<>();

 /*   @OneToMany
    @JoinColumn(name = "mat_Id")
    private List<Supplier> supplier;*/

/*    // Here we want to use this relationship to associate to every the material to the locations
    // But one location can only be used to store only one category of material at time but one material
    // can be stored to different location. That means one location for more material at time can be contradiction
    // to the reality because each single location is affected to one and only one material

    @OneToMany
    @JoinColumn(name = "materialId")
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

/*  public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }*/

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }

    public List<MaterialOrder> getMaterialOrders() {
        return materialOrders;
    }

    public void setMaterialOrders(List<MaterialOrder> materialOrders) {
        this.materialOrders = materialOrders;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
