package org.launchcode.dynamicinventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Entity
public class ExRetour {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private MMaterial material;

    @NotNull
    private double returnedQuantity;

    private Date date=new Date();

    private String origine;

    public ExRetour(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }

    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }

    public double getReturnedQuantity() {
        return returnedQuantity;
    }

    public void setReturnedQuantity(double returnedQuantity) {
        this.returnedQuantity = returnedQuantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOrigine() {
        return origine;
    }

    public void setOrigine(String origine) {
        this.origine = origine;
    }
}
