package org.launchcode.dynamicinventory.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class MaterialOrder {
    @Id
    @GeneratedValue
    private int orderId;
    //private String name;

    @ManyToOne
    private MMaterial material;

    @NotNull
    private double orderdQuantity;
    @NotNull
    private String destination;

    //@Pattern(regexp="mm-dd-yyyy")
    private Date date=new Date();

    public MaterialOrder(){}


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }

    public double getOrderdQuantity() {
        return orderdQuantity;
    }

    public void setOrderdQuantity(double orderdQuantity) {
        this.orderdQuantity = orderdQuantity;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
