package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private int supplierId;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private long phone;
    private long fax;
    private String address;

    /*@ManyToOne
    private MMaterial material;*/
    @OneToMany
    @JoinColumn(name="supplierId")
    private List<MMaterial> materials=new ArrayList<>();

    @OneToMany
    @JoinColumn(name="supplierId")
    private List<Flow>flows=new ArrayList<>();



    public Supplier(){}

    public Supplier(int supplierId, String name, String email, long phone, long fax, String address, List<MMaterial> materials){
        this.setSupplierId(supplierId);
        this.setName(name);
        this.setEmail(email);
        this.setPhone(phone);
        this.setFax(fax);
        this.setAddress(address);
        this.setMaterials(materials);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<MMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MMaterial> materials) {
        this.materials = materials;
    }

    public List<Flow> getFlows() {
        return flows;
    }

    public void setFlows(List<Flow> flows) {
        this.flows = flows;
    }
}
