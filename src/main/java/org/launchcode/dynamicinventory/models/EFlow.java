package org.launchcode.dynamicinventory.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class EFlow {
    // For the registration of the flow it is necessary to provide the corresponding material and
    // the author of the flow(fournisseur). Both of them are represented here with their id
    // which are the selected option from the view(html file)

    //@NotNull
    //private int materialId;
    //@NotNull
    //private int fournisseurId;
    @Id
    @GeneratedValue
    private int id;
    @NotNull
    private double flowQuantity;

    @ManyToOne
    private MMaterial material;

    @OneToMany
    @JoinColumn(name = "eflow")
    private List<Fournisseur> fournisseurs=new ArrayList<>();

    @NotNull
    private String description;

    public EFlow(){}

    /*@ManyToMany
    private Iterable<MMaterial>materials;

    public EFlow(){}


    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(int fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public Iterable<MMaterial> getMaterials() {
        return materials;
    }

    public void setMaterials(Iterable<MMaterial> materials) {
        this.materials = materials;
    }*/

    public List<Fournisseur> getFournisseurs() {
        return fournisseurs;
    }

    public void setFournisseurs(List<Fournisseur> fournisseurs) {
        this.fournisseurs = fournisseurs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
