package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import java.util.*;

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

    @OneToMany
    @JoinColumn(name = "materialId")
    private List<Location> locations = new ArrayList<Location>();

    @OneToMany
    @JoinColumn(name = "matId")
    private List<Flow> flows = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "matId")
    private List<Eflow> eflows = new ArrayList<Eflow>();

    //This column will be shown in the table to the material order
    @OneToMany
    @JoinColumn(name = "matId")
    private List<MaterialOrder> materialOrders = new ArrayList<MaterialOrder>();

    /*Many to many relationship consist on two one to many relationship according to the
    * http://en.tekstenuitleg.net/articles/software/database-design-tutorial/many-to-many.html
    * So the many to many  relationship between the material and the Fournisseur will be here replaced by
    * OneToMany between the entities "material" and "eflow"*/

    @ManyToMany(mappedBy = "materials")
    private List<Fournisseur>fournisseurs=new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "materialId")
    private List<InRetour> inRetours = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "materialId")
    private List<ExRetour> exRetours = new ArrayList<>();


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

    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public List<Eflow> getEflows() {
        return eflows;
    }

    public void setEflows(List<Eflow> eflows) {
        this.eflows = eflows;
    }

    public List<InRetour> getInRetours() {
        return inRetours;
    }

    public void setInRetours(List<InRetour> inRetours) {
        this.inRetours = inRetours;
    }

    public List<ExRetour> getExRetours() {
        return exRetours;
    }

    public void setExRetours(List<ExRetour> exRetours) {
        this.exRetours = exRetours;
    }
}
