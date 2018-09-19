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
    private long phone;
    private long fax;
    private String address;

    @ManyToOne
    private MMaterial material;

    public Supplier(){}

    public Supplier(int supplierId, String name, String email, long phone, long fax, String address, MMaterial material){
        this.supplierId=supplierId;
        this.name =name;
        this.email=email;
        this.phone=phone;
        this.fax=fax;
        this.address=address;
        this.material=material;
    }

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


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getFax() {
        return fax;
    }

    public void setFax(long fax) {
        this.fax = fax;
    }
}
