package org.launchcode.dynamicinventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Flow {
    @Id
    @GeneratedValue
    private int flowId;
    private String name;
    private double flowQuantity;

    @ManyToOne
    private MMaterial material;


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
}
