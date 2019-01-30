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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        model.addAttribute("alllocations",locDao.findAll());
        model.addAttribute("allEFlows", exflowDao.findAll());
        model.addAttribute("allfournisseurs", fournisseurDao.findAll());
        return "material/index";
    }
    @RequestMapping(value = {"id={materialId}"})
    public String schowMaterial(Model model, @PathVariable int materialId){
        List<MMaterial>foundListOfMaterial=new ArrayList<>();
        MMaterial foundMaterial=matDao.findByMatId(materialId);

        if(foundMaterial==null){

            model.addAttribute("foundListOfMaterial", foundListOfMaterial);
        }else{
            foundListOfMaterial.add(foundMaterial);
            model.addAttribute("foundListOfMaterial", foundListOfMaterial);
        }
        model.addAttribute("foundMaterial",foundMaterial);
        model.addAttribute("alllocations",locDao.findAll());
        model.addAttribute("title", "Result of the material with id = "+materialId);
        return "material/singleMaterialShow";
    }

    @RequestMapping(value = {"name={materialName}"})
    public String schowMaterialbyName(Model model, @PathVariable String materialName){
        List<MMaterial>foundListOfMaterial=new ArrayList<>();
        MMaterial foundMaterial=matDao.findByMatName(materialName);
        String title;
        boolean existthisMaterial=!(foundMaterial==null)? true:false;
        if(existthisMaterial==true){
            title="The search of the material with the name = '"+materialName+"' is the following";
            foundListOfMaterial.add(foundMaterial);
        }else{
            title ="No material with the name = '" +materialName+"'";
        }
        model.addAttribute("foundListOfMaterial", foundListOfMaterial);

        model.addAttribute("foundMaterial",foundMaterial);
        model.addAttribute("alllocations",locDao.findAll());
        model.addAttribute("title", title);
        return "material/singleMaterialByName";
    }

    // How to make sure that all reception of are located in the warehouse or make sure that the available
    // stock of the the material is conform or equal to the sum of the quantity of that material placed in different
    // locations
    // This function aimed to close the gap between the available stock of each material and its stored quantity
    // It can come that the sum of quantities of the stored material in different places does not match the
    // total available quantity of the material in the system. THe stapler has maybe omitted to relocate the
    // material after the reception or forget to associate e new location for the new material
    @RequestMapping(value="task")
    public String locationstask(Model model){
        List <MMaterial>listOfMaterials=new ArrayList<>();
        listOfMaterials.addAll(matDao.findAll());
        List<String>taskListOfMaterialNeedingRelocation=new ArrayList<>();


        for(MMaterial material:listOfMaterials){
            //get the stock of the material
            double availableStock=material.getStock();
            // Get the list of locations of this material if these exist
            List<Location>locationsForhisMaterial=locDao.findByMaterial(material);
            double locatedStockOfThisMaterial=0;
            List<String>actuellocationsnameforthematerial=new ArrayList<>();
            for(Location location:locationsForhisMaterial){
                //cummulation of the stock available of this material at its locations
                locatedStockOfThisMaterial+=location.getLocationStock();
            }
            double tobelocatedSock=availableStock-locatedStockOfThisMaterial;

            boolean relocationNeeded=(tobelocatedSock>0)? true:false;
            if(relocationNeeded==true){
                //Map<String, Double>materialAndQuantity=new HashMap<>();
                //materialAndQuantity.put(material.getMatName(),tobelocatedSock);

                for(Location el:locationsForhisMaterial){
                    actuellocationsnameforthematerial.add(el.getName());
                }
                String newTask=material.getMatName()+":"+tobelocatedSock+":"+actuellocationsnameforthematerial;
                taskListOfMaterialNeedingRelocation.add(newTask);

                model.addAttribute("title","Location needed for "+ tobelocatedSock + " of the material="+ material.getMatName());
            }
            else {//no material to be relocated
                model.addAttribute("title","No material to be relocated");
            }
        }
        model.addAttribute("taskListOfMaterialNeedingRelocation",taskListOfMaterialNeedingRelocation);
        return "location/taskList";
    }


    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchMaterial(Model model) {
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
        List<MMaterial>listofMaterial=new ArrayList<>();

        HashMap<String, String> searchfieldAndValue = new HashMap<>();

        boolean searchValueIsChecked=true;
        //boolean materialIsExisting=true;

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
                    } catch(NumberFormatException e) {
                        isnumeric = false;
                    }

                } else {
                    boolean isDouble = true;
                    try {
                        Double.parseDouble(searchValue);

                        searchfieldAndValue.put("availableStock", searchValue);

                    } catch (NumberFormatException e) {
                        isDouble = false;
                    }

                }
            }
            //Condition of existence of the search material in the list of existing list of material

            if (searchfieldAndValue.containsKey("materialName")==true){
                boolean materialIsExisting=matDao.findAll().contains(matDao.findByMatName(searchValue));
                listofMaterial.add(matDao.findByMatName(searchValue)); // empty if materialIsExisting==false and NotEmpty if materialIsxisting ==true
                model.addAttribute("criteria", "No material found with the given criteria name = "+searchValue);
                model.addAttribute("materials", listofMaterial);
                model.addAttribute("materialIsExisting",materialIsExisting);// True for material existing and false for empty list of materil
                model.addAttribute("title", "Result of search of the material with the name = " + searchValue);
                //return "material/edit";

            } else {
                if (searchfieldAndValue.containsKey("materialId")==true) {

                    int id = Integer.parseInt(searchValue);
                    boolean materialIsExisting=matDao.findAll().contains(matDao.findByMatId(id));

                    //listofMaterial.add(matDao.findByMatId(Integer.parseInt(searchfieldAndValue.get("materialId"))));//==
                    listofMaterial.add(matDao.findByMatId(id));

                    model.addAttribute("criteria", "No material found with the given criteria id = "+searchValue);
                    model.addAttribute("materialIsExisting",materialIsExisting);// True for material existing and false for empty list of materil
                    model.addAttribute("materials", listofMaterial);
                    model.addAttribute("title", "Result of search of the material with the id = " + searchValue);
                    //return "material/edit";

                } else {//Case of available stock
                    if (searchfieldAndValue.containsKey("availableStock") == true) {

                        double inputValue=Double.parseDouble(searchValue);
                        List<MMaterial>existingListofmaterialmatchingCriteria=matDao.findByStockLessThanEqual(inputValue);
                        boolean materialIsExisting ;
                        if(existingListofmaterialmatchingCriteria.size()==0){
                             materialIsExisting = false;
                        }else {
                             materialIsExisting = true;
                        }
                        listofMaterial.addAll(matDao.findByStockLessThanEqual(inputValue));
                        model.addAttribute("criteria", "No material found with criteria available stock = "+searchValue);
                        model.addAttribute("materialIsExisting", materialIsExisting);
                        model.addAttribute("materials", listofMaterial);
                        model.addAttribute("title", "Result of search of the material(s) with the stock  less equals to " + searchValue);

                    }
                }
            }
        }
        return "material/editList";
    }

    @RequestMapping(value = "reception", method = RequestMethod.GET)
    public String displayaddmatflow(Model model) {

        model.addAttribute("title", "Add the new flow of the material");
        model.addAttribute("material", new MMaterial());
        model.addAttribute(new Location());
        model.addAttribute(new Supplier());
        model.addAttribute(new Fournisseur());

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

        // Add the objects in relation with material
        Supplier mSupplier = supplierDao.findOne(supplierId);
        //material.setSupplier(mSupplier);
        model.addAttribute("supplier", mSupplier);
        model.addAttribute("supplierId", material.getSupplier());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Insert a new Material");

            return "material/reception";
        } else {
            // The reception of the material does not care about the date which is necessary for the flow registration.
            // Flows are the record of the movements of the material
            Flow entrymaterial = new Flow();
            Date flowDate = new Date();// Create for the registration of the flow
            //DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy");
            /*String strDate = dateFormat.format(dateFormat);*/
            // Registration of the Flow via material reception
            entrymaterial.setMaterial(matDao.findByMatName(material.getMatName()));
            entrymaterial.setFlowQuantity(receivedStock);
            entrymaterial.setDate(new Date()); // Set the date of the flow
            entrymaterial.setName("Reception " + matName);
            entrymaterial.setDescription("Reception of " + matName + flowDate);
            entrymaterial.setDestination("Warehouse");
            entrymaterial.getFlowId();
            flowDao.save(entrymaterial);

            //Supplier mSupplier = supplierDao.findBySupplierId(supplierId);
            //model.addAttribute("mSupplier",mSupplier);

            List<MMaterial> materials = mSupplier.getMaterials();
            //List<Fournisseur>fournisseurs=material.getFournisseurs();

            //creation of pseudo variable to reduce the expression
            if ((matDao.findByMatName(matName) == null)) {
                List<Fournisseur>thisMaterialFournisseurs=new ArrayList<>();
                material.setSupplier(mSupplier);//*****
                Fournisseur fournisseur = fournisseurDao.findByFournisseurId(fournisseurId);
                thisMaterialFournisseurs.add(fournisseur);
                material.setFournisseurs(thisMaterialFournisseurs);
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
                    return "redirect:/location/place/"+material.getMatName();

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

                    } else {//supplier and fournisseur never deliver this material
                        material.getFournisseurs().add(fournisseurDao.findByFournisseurId(fournisseurId));
                        List<Fournisseur> newlistfournisseurs = material.getFournisseurs();
                        material.setFournisseurs(newlistfournisseurs);
                        fournisseurDao.findByFournisseurId(fournisseurId).getMaterials().add(material);
                        matDao.findByMatId(materialId).setFournisseurs(newlistfournisseurs);
                        matDao.save(material);

                    }
                }

            }
            String flowDescription = "Reception of " + receivedStock + "  " + material.getMatName() + " from fournisseur " + fournisseurId;
            //Eflow eflow=new Eflow(material,receivedStock, flowDescription,fournisseurDao.findOne(fournisseurId));
            Eflow eflow = new Eflow();
            List<Eflow> flowsOfThisMaterial = material.getEflows();

            eflow.setQuantityflow(receivedStock);// initialise the flow quantity
            //eflow.setMaterial(matDao.findByMatName(material.getMatName()));
            eflow.setMaterial(material);
            eflow.setDescription("Reception of  " + receivedStock + "  " +material.getMatName() + " from fournisseur " + fournisseurId);
            eflow.setFournisseur(fournisseurDao.findOne(fournisseurId));
            eflow.setDate(flowDate);
            eflow.getId();

            exflowDao.save(eflow);
            flowsOfThisMaterial.add(eflow);
            material.setEflows(flowsOfThisMaterial);
            //matDao.findByMatName(matName).setEflows(flowsOfThisMaterial);

            matDao.save(material);

            return "redirect:";

        }

    }
    @RequestMapping(value="order",method=RequestMethod.GET)
    public String makeOrder(Model model){
        model.addAttribute("title","Select the materials to be ordered");
        List<MMaterial>materials=new ArrayList<>();
        List<MMaterial>stockOutMaterials=new ArrayList<>();
        for (MMaterial material:matDao.findAll()) {
            //Only the material with Stock greater than 0 will be shown
            if (material.getStock() > 0) {
                materials.add(material);
            } else {
                stockOutMaterials.add(material);
            }
        }
        model.addAttribute("outOfStockMaterials",stockOutMaterials);
        model.addAttribute("materials",materials);
        return "material/neworder";
    }
    @RequestMapping(value="order",method=RequestMethod.POST)
    public String makeOrder(Model model, @RequestParam int[] materialIds, @RequestParam double[] quantities){
        for(int i=0;i < materialIds.length; i++){
            
        }



    }

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
