package org.launchcode.dynamicinventory.models;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Eflow {


        // For the registration of the flow it is necessary to provide the corresponding material and
        // the author of the flow(fournisseur). Both of them are represented here with their id
        // which are the selected option from the view(html file)

        //@NotNull
        //private int materialId;
        //@NotNull
        //private int fournisseurId;

        @NotNull
        private Date date=new Date();

        @Id
        @GeneratedValue
        private int id;

        @NotNull
        private double quantityflow;


        @ManyToOne
        private MMaterial material;

        @ManyToOne
        private Fournisseur fournisseur;

        @NotNull
        private String description;
        /*@Basic
        private Date date;*/
        public Eflow(String date,MMaterial material, double quantityflow, String description, Fournisseur fournisseur){

            this.material=material;
            this.quantityflow=quantityflow;
            this.fournisseur=fournisseur;
            this.description=description;
        }

    public Eflow(){}



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public MMaterial getMaterial() {
        return material;
    }

    public void setMaterial(MMaterial material) {
        this.material = material;
    }

    public Fournisseur getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseur fournisseur) {
        this.fournisseur = fournisseur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public double getQuantityflow() {
        return quantityflow;
    }

    public void setQuantityflow(double quantityflow) {
        this.quantityflow = quantityflow;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
