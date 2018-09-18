package org.launchcode.dynamicinventory.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private int supplierId;

    private String name;

    private String email;
    private long phonenumber;
    private long faxnumber;
    private String address;

    @ManyToOne
    private MMaterial material;

    public Supplier(){}

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public long getFaxnumber() {
        return faxnumber;
    }

    public void setFaxnumber(long faxnumber) {
        this.faxnumber = faxnumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
