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
import java.util.*;

@Controller
@RequestMapping(value = "material")
public class MaterialController {
    @Autowired
    private MatDao matDao;

    @Autowired
    private FlowDao flowDao;

    @Autowired
    private ExflowDao exflowDao;

    @Autowired
    private LocDao locDao;

    @Autowired
    private SupplierDao supplierDao;
    @Autowired
    private IntorderDao internOrderDao;

    @Autowired
    private FournisseurDao fournisseurDao;


    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Warehouse management");
        model.addAttribute("materials", matDao.findAll());
        return "material/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("title", "search the material(s) with specific term");
        return "material/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String editsearch(Model model, HttpServletRequest request) {
        String searchTerm = request.getParameter("searchTerm");
        String searchValue = request.getParameter("searchValue");
        model.addAttribute("title", "The result of your search ");
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("searchValue", searchValue);
        String inputCategory;
        String inputValueToSearch;
        HashMap<String, String> searchfieldAndValue = new HashMap<>();
        if ((searchTerm != null && searchValue.length() != 0)) {
            inputCategory = searchTerm;
            inputValueToSearch = searchValue;
            if (searchTerm.equalsIgnoreCase("materialName")) {
                searchfieldAndValue.put(searchTerm, searchValue);
            } else {
                if (searchTerm.equalsIgnoreCase("materialId")) {
                    boolean isnumeric = true;
                    try {
                        Integer.parseInt(searchValue);

                        searchfieldAndValue.put("materialId", searchValue);
                    } catch (NumberFormatException e) {
                        isnumeric = false;
                    }

                } else {
                    boolean isDouble = true;
                    try {
                        Integer.parseInt(searchValue);

                        searchfieldAndValue.put("materialStock", searchValue);
                        ;
                    } catch (NumberFormatException e) {
                        isDouble = false;
                    }

                }
            }

            if (searchfieldAndValue.containsKey("materialName")) {
                model.addAttribute("material", matDao.findByMatName(searchfieldAndValue.get("materialName")));
                model.addAttribute("title", "Result of search of the material with the name " + searchValue);
                return "material/edit";

            } else {
                if (searchfieldAndValue.containsKey("materialId")) {
                    int id = Integer.parseInt(searchValue);
                    model.addAttribute("material", matDao.findByMatId(id));
                    model.addAttribute("title", "Result of search of the material with the id" + searchValue);
                    return "material/edit";

                } else {
                    model.addAttribute("materials", matDao.findByStockLessThanEqual(Double.parseDouble(searchValue)));
                    model.addAttribute("title", "Result of search of the material(s) with the stock " + searchValue);
                    return "material/editList";
                }
            }
        }
    }
        /*if(searchTerm=="materialId"){
            boolean isnumeric = true;

            try {
                Integer.parseInt(searchValue);
            } catch (NumberFormatException e) {
                isnumeric = false;
            }
        }
            if ((searchTerm.isEmpty() || searchValue.isEmpty())) {
            inputCategory="";
            inputValueToSearch= "";

            model.addAttribute("title", "You didn't enter the search value.Try to your search term and make sure to enter it");
            return "material/search";
        } else {
            if (searchTerm.equalsIgnoreCase("materialName")) {
                 inputCategory=searchTerm;
                 inputValueToSearch= searchValue;

                model.addAttribute("material", matDao.findByMatName(searchValue));
                model.addAttribute("title", "Result of search of the material with the name " + searchValue);
                return "material/edit";
            } else {
                if (searchTerm.equalsIgnoreCase("materialId")) {

                    boolean isnumeric = true;

                    try {
                        int id = Integer.parseInt(searchValue);
                        model.addAttribute("material", matDao.findByMatId(id));
                        model.addAttribute("title", "Result of search of the material with the id" + searchValue);
                        return "material/edit";

                    } catch (NumberFormatException e) {
                        isnumeric = false;
                        model.addAttribute("title", "Entered value is not a number.Try to enter a number your search term and make sure to enter it");
                        return "material/search";
                    }

                } else {
                    model.addAttribute("materials", matDao.findByStockLessThanEqual(Double.parseDouble(searchValue)));
                    model.addAttribute("title", "Result of search of the material(s) with the stock " + searchValue);
                    return "material/editList";
                }

*//*        model.addAttribute("material",matDao.findByMatName(searchValue));
        return "material/edit";*//*
            }}}*/


    @RequestMapping(value = "reception", method = RequestMethod.GET)
    public String displayaddmatflow(Model model) {

        model.addAttribute("title", "Add the new flow of the material");
        model.addAttribute("material", new MMaterial());
        model.addAttribute(new Location());
        model.addAttribute(new Supplier());

        model.addAttribute("suppliers", supplierDao.findAll());
        model.addAttribute("locations", locDao.findAll());
        model.addAttribute("locationwithstock", locDao.findByLocationStock(0));
        model.addAttribute("fournisseurs", fournisseurDao.findAll());
        return "material/reception";
    }


    @RequestMapping(value = "reception", method = RequestMethod.POST)
    public String processaddmaterial(Model model,
                                     @ModelAttribute @Valid MMaterial material,
                                     Errors errors, @RequestParam("stock") double receivedStock,
                                     @RequestParam String matName, @RequestParam("supplierId") int supplierId,
                                     @RequestParam("fournisseurId") int fournisseurId) {
        //@RequestParam("locationId") int locationId,
        // HttpServletRequest request) {


        Supplier mSupplier = supplierDao.findOne(supplierId);
        //material.setSupplier(mSupplier);
        model.addAttribute("supplier", mSupplier);
        model.addAttribute("supplierId", material.getSupplier());

        //List<Fournisseur>fournisseur

        if (errors.hasErrors()) {
            model.addAttribute("title", "Insert a new Material");

            return "material/reception";
        } else {

            Flow entrymaterial = new Flow();
            Date flowDate = new Date();
            // Registration of the Flow
            // (matDao.findByMatName(matName)).getMatId();   //request the id of the material
            entrymaterial.setMaterial(matDao.findByMatName(material.getMatName()));
            entrymaterial.setFlowQuantity(receivedStock);

            entrymaterial.setName("Reception " + matName);
            entrymaterial.setDescription("Reception of " + matName + flowDate);
            entrymaterial.getFlowId();
            flowDao.save(entrymaterial);

            //Supplier mSupplier = supplierDao.findBySupplierId(supplierId);
            //model.addAttribute("mSupplier",mSupplier);

            List<MMaterial> materials = mSupplier.getMaterials();

            //List<Location>locations=material.getLocations();

            //creation of pseudo variable to reduce the expression
            if ((matDao.findByMatName(matName) == null)) {

                material.setSupplier(mSupplier);//*****
                Fournisseur fournisseur = fournisseurDao.findByFournisseurId(fournisseurId);
                material.getFournisseurs().add(fournisseur);
                material.setFournisseurs(material.getFournisseurs());
                // the material does not exist; the supplier is supposed to exist because it was choose in the drop down
                // So the supplier registration must be done before the materials'
                /*Todo here is to  save the new material and to add it to the list of materials delivered
                  by this supplier.Here e suppose the the supplier exist already */

                //material.setMatId(material.getMatId()); // get  and set the id for the new material
                matDao.save(material);                    // the new material has been saved
                materials.add(material);
                mSupplier.setMaterials(materials);
                supplierDao.findBySupplierId(supplierId).setMaterials(materials);


                //LOCATION// canceled after changing the relationship between material(One) and location(many) to One to Many
                /* The new material is both saved and added to the list of materials delivered by this supplier.
                Now we have to find the place in the to store the received material: Location management*/

                return "redirect:/location/add";


            } else { //Here the material exist already in the system. we have to test here if this  material is new or old for this supplier
                // Todo : before all update, we have to update the stock of the material and then // if the material is new for this supplier, we add it to the list of his delivered material
                double oldStock = matDao.findByMatName(material.getMatName()).getStock(); //get the existing stock before the reception from the database
                double newStock = oldStock + receivedStock;
                material.setStock(newStock);  // This material exists already. Update the stock after the reception
                int materialId = matDao.findByMatName(material.getMatName()).getMatId(); // The material was updated and need to be saved.To avoid the duplication, we need the Id

                /*The material exists in the system before this reception but as the Id is autogenerated for each flow or material,
                the computer has generated new id for this received material like it doesn't in the system before.To avoid this
                duplication in our database, we need to set the Id of the material to the existing material Id and then proceed
                to set the stock and save the update.*/

                material.setMatId(materialId); // Set the Id to the existing materialId
                matDao.findByMatId(materialId).setStock(newStock);// Update the stock
                matDao.save(material); // Save the update in the database

                //exflowDao.save(eflow);

                //if (supplierDao.findBySupplierId(material.getSupplier().getSupplierId()).getMaterials().contains(material.getMatName()) == true) {
                if (materials.contains(material) == true && material.getFournisseurs().contains(fournisseurDao.findByFournisseurId(fournisseurId)) == true) {
                    //TODO: redirect to "material/" = return "redirect:"
                    // material with the name matName or id supplierId exists already and this supplier has once delivered
                    // it to and the so the only things to do here is
                    // The update the available quantity is already done. No update of the list of the supplied material by this supplier

                    String suppliermaterialsmessage = "No Add this material to the list of the delivered materials by this supplier";


                    //No more update of fournisseur list of this material
                    //return "redirect:";
                    model.addAttribute("title", "Add new location for the new product received");
                    return "redirect:/location/add";

                } else {
                    if (materials.contains(material) == false && material.getFournisseurs().contains(fournisseurDao.findByFournisseurId(fournisseurId)) == true) {
                        // supplierDao.findByName(material.getSupplier().getName()).getMaterials().contains(material) == false)
                        // material and the supplier exist already. But this material does not exist in the list of the supplied
                        // material by this supplier
                        // To do is to update the quantity of the material and update the list of materials for this supplier
                        // No more update of fournisseur list of this material
                        materials.add(material);
                        mSupplier.setMaterials(materials);
                        supplierDao.findBySupplierId(supplierId).setMaterials(materials);
                        supplierDao.save(mSupplier);

                        //return "redirect:";

                    } else {
                        material.getFournisseurs().add(fournisseurDao.findByFournisseurId(fournisseurId));
                        List<Fournisseur> newlistfournisseurs = material.getFournisseurs();
                        material.setFournisseurs(newlistfournisseurs);

                        matDao.findByMatId(materialId).setFournisseurs(newlistfournisseurs);
                        matDao.save(material);

                    }
                }

            }
            String flowDescription = "Reception of " + receivedStock + material.getMatName() + " from fournisseur " + fournisseurId;
            //Eflow eflow=new Eflow(material,receivedStock, flowDescription,fournisseurDao.findOne(fournisseurId));
            Eflow eflow = new Eflow();
            List<Eflow> flowsOfThisMaterial = material.getEflows();

            eflow.setQuantityflow(receivedStock);// initialise the flow quantity
            //eflow.setMaterial(matDao.findByMatName(material.getMatName()));
            eflow.setMaterial(material);
            //eflow.setQuantityflow(receivedStock);
            eflow.setDescription("Reception of " + receivedStock + material.getMatName() + " from fournisseur " + fournisseurId);
            eflow.setFournisseur(fournisseurDao.findOne(fournisseurId));
            eflow.getId();

            //eflow.setEid(eflow.getEid());
            exflowDao.save(eflow);
            flowsOfThisMaterial.add(eflow);
            material.setEflows(flowsOfThisMaterial);
            //matDao.findByMatName(matName).setEflows(flowsOfThisMaterial);

            matDao.save(material);

            return "redirect:";

        }

    }

    /*    @RequestMapping(value = "receiving", method = RequestMethod.GET)
        public String displayreceiving(Model model){

            model.addAttribute("title", "Add the new flow of the material");
            model.addAttribute("material",new MMaterial());
            model.addAttribute(new Location());
            model.addAttribute(new Fournisseur());

            model.addAttribute("eflows", exflowDao.findAll());
            model.addAttribute("locations", locDao.findAll());
            return "material/reception";


        }*/
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String removeMaterial(Model model) {
        model.addAttribute("title", "Remove one or more material from the list");
        model.addAttribute("materials", matDao.findAll());
        //model.addAttribute("founisseurs",fournisseurDao.findAll());
        return "material/removematerial";

    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String removeMaterial(Model model, HttpServletRequest request) {
        //@RequestParam("fournisseurs") List<Fournisseur>founisseurs ){
        List<Integer> selectedMaterial = new ArrayList<Integer>();
        String[] listSelectedMaterialIds;
        listSelectedMaterialIds = request.getParameterValues("materialId");
        for (String materialId : listSelectedMaterialIds) {
            //int materialid=Integer.parseInt(materialId);
            MMaterial material = matDao.findByMatId(Integer.parseInt(materialId));

            List<Location> materialLocations = material.getLocations();
            for (Location location : materialLocations) {
                locDao.delete(location);
            }
            matDao.delete(material);
        }
        return "redirect:";
    }

}
