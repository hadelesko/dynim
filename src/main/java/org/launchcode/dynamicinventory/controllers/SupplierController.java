package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.Supplier;
import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "supplier")
public class SupplierController {

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
        model.addAttribute("title", "The list of the suppliers");
        model.addAttribute("suppliers", supplierDao.findAll());
        model.addAttribute("materials", matDao.findAll());
        return "supplier/index";
    }
    @RequestMapping(value="search", method=RequestMethod.GET)
    public String editresultsearch(Model model){
        model.addAttribute("title","Search supplier");
        return "supplier/searchsupplier";
    }
    @RequestMapping(value="search", method=RequestMethod.POST)
    public String editresultsearchprozess(Model model, HttpServletRequest request){

        String name=request.getParameter("name");
        model.addAttribute("name",name );

        model.addAttribute("supplier",supplierDao.findByName(name));
        if(supplierDao.findByName(name)!=null){
            Supplier supplier=supplierDao.findByName(name);
            model.addAttribute("supplier", supplier);
            //int supplierId=supplier.getSupplierId();
            //model.addAttribute("supplierId",supplierId);
            model.addAttribute("title","Result of the Search of the supplier "+ name);
            return "supplier/editsupplier";
        }else{
            model.addAttribute("title","Result of the Search of the supplier "+ name + "does not exist. Enter another name");

            return "redirect:";
        }

    }


/*    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addsupplierdisplayform(Model model) {
        model.addAttribute("title", "The list of the suppliers");
        model.addAttribute(new Supplier());
        model.addAttribute("suppliers", supplierDao.findAll());
        model.addAttribute(new MMaterial());
        return "supplier/addsupplier";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addsupplierprocessform(Model model,
                                         @ModelAttribute @Valid Supplier supplier,
                                         Errors errors, @RequestParam("name") String name,
                                         @RequestParam String nmaterialId,
                                         @RequestParam String matName) {
        //In summary, parseInt(String) returns a primitive int, whereas valueOf(String) returns a new Integer() object.
        int materialId = Integer.valueOf(nmaterialId);

        //@RequestParam String name,
        //String name= supplier.getName();
        //String returnFormOrUrl;
        if (errors.hasErrors()) {
            model.addAttribute("title", "make sure to fill th required fields. Correct your form");
            return "supplier/addsupplier";
        } else {
            MMaterial mat = new MMaterial();
            *//*supplierDao.findByName(name)--->return supplier/    +.getMaterials()--->returns supplied  materials
             +.contains(matdao.findByMatId(materialId)) ----> returns boolean true or false
            * *//*

            //
            if (supplierDao.findByName(name) != null && supplierDao.findByName(name).getMaterials().contains(matDao.findByMatId(materialId)) == false) {
                // above : if supplier exist && delivered material is not yet recorded for him ?Then add the new material
                // to his list of delivered material
                //String supplierName = name;
                //int supplierId = supplierDao.findByName(name).getSupplierId();

                //supplier exist already but new material
                // Todo is to check if he has already delivered this material or if it is the first time
                // If has already delivered that material, we don't need to record this supplier again.
                // if the delivered material is not in his list of material, then add the materialId to the list
                // of the material delivered by this supplier


                List<MMaterial> listDeliveredMaterial = supplier.getMaterials();//supplied by the supplier

                mat.getMatId();
                listDeliveredMaterial.add(mat);
                supplier.setMaterials(listDeliveredMaterial);
                supplierDao.save(supplier);

                return "redirect:";
                //return returnFormOrUrl;

                //return "redirect:";
            }
            // here the supplier exists already and he has already delivered this material to the company
            // then nothing more to do the supplier and that material are already in the our system.
            if (supplierDao.findByName(name) != null && supplierDao.findByName(name).getMaterials().contains(matDao.findByMatId(materialId)) == true) {
                return "redirect:";
                // return returnFormOrUrl;
            }

            //This supplier have never delivered us. This material is till now delivered by other suppliers
            //Todo is to add this supplier to the list of suppliers of this material
            if (supplierDao.findByName(name) == null && supplierDao.findByName(name).getMaterials().contains(matDao.findByMatId(materialId)) == true) {
                supplier.getSupplierId();
                supplierDao.save(supplier);
                return "redirect:";
                // return returnFormOrUrl;
                //return "redirect:";
            }


            //A new supplier with new material for the company
            // Todo here is to record the new material and new supplier in our system and
            //record of the new supplier
            mat.getMatId();

            if (supplierDao.findByName(name) == null && supplierDao.findByName(name).getMaterials().contains(matDao.findByMatId(materialId)) == false) {
                //||(matDao.findAll().contains(matDao.findByMatName(supplier.getMaterials().get(mat.getMatId()){

                supplier.getMaterials().add(mat);
                //supplier.getSupplierId();                                                                                                        supplier.getSupplierId();
                supplierDao.save(supplier);

                //record of the new material
                //return "redirect:material/add"; //mat.getMatId()
                return "redirect:material/add";
                //return returnFormOrUrl;

            }
        }
        return "redirect:";
    }*/

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addSupplierForm(Model model) {
        model.addAttribute("title", "Add a new supplier to the list of the suppliers");
        model.addAttribute(new Supplier());
        model.addAttribute("suppliers", supplierDao.findAll());
        //model.addAttribute("actuellistofmaterials", matDao.findAll());
        //model.addAttribute(new MMaterial());
        return "supplier/addsupplier";

    }
    @RequestMapping(value="add", method=RequestMethod.POST)
    public String addsupplierformprocess(Model model, @ModelAttribute @Valid Supplier supplier, @RequestParam String name, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("title", "make sure to fill th required fields. Correct your form");
            return "supplier/addsupplier";
        }else{
        List<Supplier>presentlistsuppliers=supplierDao.findAll();
        for(Supplier oldsupplier:presentlistsuppliers){
            if(oldsupplier.getName().equalsIgnoreCase(name)){
                model.addAttribute("supplierexistsalredy",supplierDao.findByName(name)); //this will be prompted by javascript alert
                return "redirect:search";
            }
        }
        supplierDao.save(supplier);
        return "redirect:";
    }

}}





























