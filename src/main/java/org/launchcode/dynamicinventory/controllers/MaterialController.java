package org.launchcode.dynamicinventory.controllers;

/*import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.data.FlowDao;
import org.launchcode.dynamicinventory.data.LocDao;
import org.launchcode.dynamicinventory.data.MatDao;
import org.launchcode.dynamicinventory.data.SupplierDao;*/

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.dynamicinventory.models.Flow;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.Supplier;
import org.launchcode.dynamicinventory.models.data.FlowDao;
import org.launchcode.dynamicinventory.models.data.LocDao;
import org.launchcode.dynamicinventory.models.data.MatDao;
import org.launchcode.dynamicinventory.models.data.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "material")
public class MaterialController {
    @Autowired
    private MatDao matDao;

    @Autowired
    private FlowDao flowDao;

    @Autowired
    private LocDao locDao;

    @Autowired
    private SupplierDao supplierDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Warehouse management");
        model.addAttribute("materials", matDao.findAll());
        return "material/index";
    }

    @RequestMapping(value = "reception", method = RequestMethod.GET)
    public String displayaddmatflow(Model model) {

        model.addAttribute("title", "Add the new flow of the material");
        model.addAttribute("material", new MMaterial());


        model.addAttribute("suppliers", supplierDao.findAll());
        model.addAttribute("locations", locDao.findAll());
        model.addAttribute("locationwithstock", locDao.findByLocationStock(0));
        return "material/reception";
    }


    @RequestMapping(value = "reception", method = RequestMethod.POST)
    public String processaddmaterial(Model model,
                                     @ModelAttribute @Valid MMaterial material,
                                     Errors errors, @RequestParam("stock") double stock,
                                     @RequestParam String matName, @RequestParam("supplierId") int supplierId, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Insert a new Material");

            return "material/reception";
        } else {


            Flow entrymaterial = new Flow();

            // Registration of the Flow
            ///(matDao.findByMatName(matName)).getMatId(); // request the id of the material
            entrymaterial.setMaterial(matDao.findByMatName(material.getMatName()));
            entrymaterial.setFlowQuantity(stock);
            entrymaterial.setName("reception");
            entrymaterial.getFlowId();
            flowDao.save(entrymaterial);

            Supplier thissupplier = supplierDao.findBySupplierId(supplierId);
            List<MMaterial>materials=thissupplier.getMaterials();

            //creation of pseudo variable to reduce the expression
            if ((matDao.findByMatName(matName) == null)) {
            //if ((matDao.findByMatName(material.getMatName()) == null)) {


                // the material does not exist; the supplier is supposed to exist because it was choose in the drop down
                // So the supplier registration must be done before the materials'
                // Todo here is to implement something to oblige the user to search the for supplier before the reception of the material
                material.setMatId(material.getMatId());   //get  and set the id for the new material
                matDao.save(material);                    // the new material has been saved

                //Add the new material to the list of the delivered by this supplier

                /*supplierDao.findBySupplierId(material.getSupplier().getSupplierId()).getMaterials().add(material);
                supplierDao.findBySupplierId(material.getSupplier().getSupplierId())
                        .setMaterials(supplierDao.findBySupplierId(material.getSupplier().getSupplierId())
                        .getMaterials());*/

                materials.add(material);
                thissupplier.setMaterials(materials);
                supplierDao.findBySupplierId(supplierId).setMaterials(materials);

                //return "redirect:";

            } else { //Here the material exist already in the system. we have to test here if this  material is new or old for this supplier
                // Todo : before all update, we have to update the stock of the material and then // if the material is new for this supplier, we add it to the list of his delivered material

                double newstocko = matDao.findByMatName(material.getMatName()).getStock() + stock;
                matDao.findByMatName(material.getMatName()).setStock(newstocko);
                //matDao.save(material);

                //Supplier thissupplier = supplierDao.findBySupplierId(supplierId);
                //String suppliername = thissupplier.getName();

                //List<MMaterial> listMaterialsuppliedBythissupplier = thissupplier.getMaterials();

                //if (supplierDao.findBySupplierId(material.getSupplier().getSupplierId()).getMaterials().contains(material.getMatName()) == true) {
                if(materials.contains(material) == true) {
                    //TODO: redirect to "material/" = return "redirect:"
                    // material with the name matName or id supplierId exists already and this supplier has once delivered
                    // it to and the so the only things to do here is
                    // The update the available quantity is already done. No update of the list of the supplied material by this supplier

                    String suppliermaterialsmessage = "No Add this material to the list of the delivered materials by this supplier";
                    //does this supplier have this material in his list of the delivered materials?
                    //if(!supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material.getMatId())){

                    //return "redirect:";

                } else { //supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material) == false)
                    //material and the supplier exist already. But this material does not exist in the list of the supplied material by this supplier
                    // To do is to update the quantity of the material and update the list of matereials for this supplier

                    materials.add(material);
                    thissupplier.setMaterials(materials);
                    supplierDao.findBySupplierId(supplierId).setMaterials(materials);
                    

                    String sname = request.getParameter("supplierName");
                    //supplierDao.findBySupplierId(material.getSupplier().getSupplierId()).getMaterials().add(material);

                    //supplierDao.findBySupplierId(supplierId).getMaterials().add(material);
                    //supplierDao.save(thissupplier);
                    //supplierDao.findBySupplierId(supplierId).setMaterials(supplierDao.findBySupplierId(supplierId).getMaterials());

                    //matDao.save(matDao.findByMatName(matName));
                    /*thissupplier.getMaterials().add(material);
                    thissupplier.setMaterials(thissupplier.getMaterials());*/
                    //supplierDao.save(thissupplier);*/


                        /*supplierDao.findByName(material.getSupplier().getName()).getMaterials().add(material);

                        //set the list of the materials delivered by this supplier database
                        supplierDao.findByName(material.getSupplier().getName()).setMaterials(supplierDao.findByName(material.getSupplier().getName()).getMaterials());

                        matDao.save(matDao.findByMatName(matName));*/

                        //Copy of the existing list of material in the new list
/*                        List<MMaterial>listmaterialforthissupplier=new ArrayList<>();
                        thissupplier.getMaterials().add(material);
                        List<MMaterial>oldmateriallistforthissupplier=thissupplier.getMaterials();
                        for(MMaterial mat:oldmateriallistforthissupplier){
                            listmaterialforthissupplier.add(mat);
                        }*/
                        // update the List of material by this supplier

                        //thissupplier.setMaterials(listmaterialforthissupplier);




                    //material.setMatId(material.getMatId());
                    //matDao.save(material);

                    //return "redirect:";
                }

            }
            return "redirect:";
        }

    }

}




            /*//material.getSupplier().getSupplierId();
             //if ((matDao.findByMatName(matName) != null) && (supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material.getMatId()) == true)){
            if ((matDao.findByMatName(matName) != null) && listMaterialsuppliedBythissupplier.contains(material.getMatId())==true){
                 //if (!(supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material.getMatId()))) { //it was checked that the
                // material with the name matName exists so the only things to do here is
                //// to update the available quantity and to update le list of the supplied material by this supplier


                double newstocko = matDao.findByMatName(matName).getStock() + stock;
                matDao.findByMatName(matName).setStock(newstocko);
                matDao.save(matDao.findByMatName(matName));
                String suppliermaterialsmessage = "";
                //does this supplier have this material in his list of the delivered materials?
                //if(!supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material.getMatId())){
                suppliermaterialsmessage = "Add this material to the list of the delivered materials by this supplier";
                supplierDao.findByName(material.getSupplier().getName()).getMaterials().add(material);
                //set the list of the materials delivered by this supplier database
                supplierDao.findByName(material.getSupplier().getName()).setMaterials(supplierDao.findByName(material.getSupplier().getName()).getMaterials());
                 return "redirect:";

            } else{
                if(matDao.findByMatName(matName) != null && supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material) == false) {
                    //material and the supplier exist already. It exist also already in the list of the supplied material by this supplier

                    // if (supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material.getMatId())) {
                    //suppliermaterialsmessage="The supplier used already to deliver this material. no more need to add it";


                    double newstocko = matDao.findByMatName(matName).getStock() + stock;
                    matDao.findByMatName(matName).setStock(newstocko);
                    matDao.save(matDao.findByMatName(matName));
                    //material.setMatId(material.getMatId());
                    //matDao.save(material);
                    return "redirect:";

                } else{

                    //if (matDao.findByMatName(matName) == null) {


                    // the material does not exist; the supplier is supposed to exist because it was choose in the drop down
                    // So the supplier registration must be done before the materials'
                    // Todo here is to implement something to oblige the user to search the for supplier before the reception of the material
                    material.setMatId(material.getMatId());   //get  and set the id for the new material
                    matDao.save(material); // the new material has been saved
                    //add the new material to the list of the delivered by this supplier
                    supplierDao.findByName(material.getSupplier().getName()).getMaterials().add(material);
                    return "redirect:";

                }
            }
                }

            }


        }*/


















































             /*   else{
                    //New material that is at the first time in the database
                    //Obtain the id for the new material that will help to save it into the database
                    material.setMatId(material.getMatId());
                    //save the new material
                    matDao.save(material);
                    return "redirect:";
                }*/






