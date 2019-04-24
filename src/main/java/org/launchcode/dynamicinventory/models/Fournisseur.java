package org.launchcode.dynamicinventory.models;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Fournisseur {
    @Id
    @GeneratedValue
    private int fournisseurId;
    @NotNull
    private String name;
    @NotNull
    private String email;
    private long phone;
    private long fax;
    private String address;
    @OneToMany
    @JoinColumn(name = "fournisseurId")
    private List<Eflow>eflows=new ArrayList<>();

    @ManyToMany
    private List<MMaterial> materials;

/*    @ManyToOne
    private EFlow eflow; //Many flow of material to one Fournisseur. Case of reception of different
    // materials sent by one supplier/fournisseur. The object Eflow represent any single movement of
    // material(reception of material from supplier, retour of material to the supplier)*/



    public Fournisseur() {
    }

    public int getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        this.fournisseurId = fournisseurId;
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

    public List<Eflow> getEflows() {
        return eflows;
    }

    public void setEflows(List<Eflow> eflows) {
        this.eflows = eflows;
    }

    public List<MMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MMaterial> materials) {
        this.materials = materials;
    }
}