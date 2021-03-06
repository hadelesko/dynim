package org.launchcode.dynamicinventory.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Flow {
    @Id
    @GeneratedValue
    private int flowId;
    private String name;//Reception +name of the material+ the quantity received+date
    private String description; // Reception +name of the material+ the quantity received+date
    @NotNull
    private String destination;

    @NotNull
    private double flowQuantity;

    @ManyToOne
    private MMaterial material;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Supplier supplier;


    private Date date;


    public Flow(){}


    public int getFlowId() {
        return flowId;
    }

    public void setFlowId(int flowId) {
        this.flowId = flowId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getFlowQuantity() {
        return flowQuantity;
    }

    public void setFlowQuantity(double flowQuantity) {
        this.flowQuantity = flowQuantity;
    }

    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
