package org.launchcode.dynamicinventory.controllers;

/*import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.data.FlowDao;
import org.launchcode.dynamicinventory.data.LocDao;
import org.launchcode.dynamicinventory.data.MatDao;
import org.launchcode.dynamicinventory.data.SupplierDao;*/

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.dynamicinventory.models.*;
import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private IntorderDao internOrderDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Warehouse management");
        model.addAttribute("materials", matDao.findAll());
        return "material/index";
    }

    @RequestMapping(value="search", method=RequestMethod.GET)
    public String search(Model model){
        return "material/search";
    }
    @RequestMapping(value="search",method=RequestMethod.POST)
    public String editsearch(Model model, HttpServletRequest request){
        String searchTerm=request.getParameter("searchTerm");
        model.addAttribute("title", "The result of your search ");
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("material",matDao.findByMatName(searchTerm));
        return "material/edit";
    }

    @RequestMapping(value = "reception", method = RequestMethod.GET)
    public String displayaddmatflow(Model model) {

        model.addAttribute("title", "Add the new flow of the material");
        model.addAttribute("material",new MMaterial());
        model.addAttribute(new Location());
        model.addAttribute(new Supplier());

        model.addAttribute("suppliers", supplierDao.findAll());
        model.addAttribute("locations", locDao.findAll());
        model.addAttribute("locationwithstock", locDao.findByLocationStock(0));
        return "material/reception";
    }


    @RequestMapping(value = "reception", method = RequestMethod.POST)
    public String processaddmaterial(Model model,
                                     @ModelAttribute @Valid MMaterial material,
                                     Errors errors, @RequestParam("stock") double stock,
                                     @RequestParam String matName, @RequestParam("supplierId") int supplierId){
                                     //@RequestParam("locationId") int locationId,
                                    // HttpServletRequest request) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Insert a new Material");

            return "material/reception";
        } else {

            Flow entrymaterial = new Flow();

            // Registration of the Flow
            ///(matDao.findByMatName(matName)).getMatId();   //request the id of the material
            entrymaterial.setMaterial(matDao.findByMatName(material.getMatName()));
            entrymaterial.setFlowQuantity(stock);
            entrymaterial.setName("reception");
            entrymaterial.getFlowId();
            flowDao.save(entrymaterial);

            //Location mlocation =locDao.findByLocationId(locationId);
            //model.addAttribute("mlocation", mlocation);
            //Supplier msupplier=supplierDao.findBySupplierId(supplierId);
            //model.addAttribute("msupplier", msupplier);

            Supplier thissupplier = supplierDao.findBySupplierId(supplierId);
            model.addAttribute("thissupplier",thissupplier);

            List<MMaterial>materials=thissupplier.getMaterials();
            List<Location>locations=material.getLocations();

            //creation of pseudo variable to reduce the expression
            if ((matDao.findByMatName(matName) == null)) {

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



                //***LOCATION// canceled after changing the relationship between material(One) and location(many) to One to Many

                /*Location location=material.getLocations();
                List<Location> locations=locDao.findByLocationStock(0);
                List<MMaterial>newmaterialListforthislocation=new ArrayList<MMaterial>();

                model.addAttribute("locations",locations);
                double variationStock=Double.parseDouble(request.getParameter("flowquantity"));

                location.setLocationStock(variationStock);
                locations.add(location);
                newmaterialListforthislocation.add(material);
                location.setMaterials(newmaterialListforthislocation);
                //material.setLocation(location);

                locDao.save(location);*/
                //matDao.save(material);

                //return "redirect:";

            } else { //Here the material exist already in the system. we have to test here if this  material is new or old for this supplier
                // Todo : before all update, we have to update the stock of the material and then // if the material is new for this supplier, we add it to the list of his delivered material

                double newstocko = matDao.findByMatName(material.getMatName()).getStock() + stock;
                matDao.findByMatName(material.getMatName()).setStock(newstocko);
                //matDao.save(material);

                //if (supplierDao.findBySupplierId(material.getSupplier().getSupplierId()).getMaterials().contains(material.getMatName()) == true) {
                if(materials.contains(material) == true) {
                    //TODO: redirect to "material/" = return "redirect:"
                    // material with the name matName or id supplierId exists already and this supplier has once delivered
                    // it to and the so the only things to do here is
                    // The update the available quantity is already done. No update of the list of the supplied material by this supplier

                    String suppliermaterialsmessage = "No Add this material to the list of the delivered materials by this supplier";

                    //return "redirect:";

                } else { //supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material) == false)
                    //material and the supplier exist already. But this material does not exist in the list of the supplied material by this supplier
                    // To do is to update the quantity of the material and update the list of matereials for this supplier

                    materials.add(material);
                    thissupplier.setMaterials(materials);
                    supplierDao.findBySupplierId(supplierId).setMaterials(materials);


                    //return "redirect:";
                }

            }
            return "redirect:";
        }

    }




}






















































             /*   else{
                    //New material that is at the first time in the database
                    //Obtain the id for the new material that will help to save it into the database
                    material.setMatId(material.getMatId());
                    //save the new material
                    matDao.save(material);
                    return "redirect:";
                }*/






