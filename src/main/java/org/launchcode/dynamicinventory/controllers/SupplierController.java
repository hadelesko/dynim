package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.Supplier;
import org.launchcode.dynamicinventory.models.data.FlowDao;
import org.launchcode.dynamicinventory.models.data.MatDao;
import org.launchcode.dynamicinventory.models.data.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value="supplier")
public class SupplierController {

    @Autowired
    SupplierDao supplierDao;

    @Autowired
    private MatDao matDao;

    @Autowired
    private FlowDao flowDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "The list of the suppliers");
        model.addAttribute("suppliers",supplierDao.findAll());
        model.addAttribute("materials",matDao.findAll());
        return "supplier/index";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addsupplierdisplayform(Model model){
        model.addAttribute("title", "The list of the suppliers");
        model.addAttribute("suppliers",supplierDao.findAll());
        return "supplier/addsupplier";
    }
    @RequestMapping(value = "reception", method = RequestMethod.POST)
    public String addsupplierprocessform(Model model,
                                         @ModelAttribute @Valid Supplier supplier,
                                         Errors errors,@RequestParam("name") String name, @RequestParam("materialId") int materialId) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "make sure to fill th required fields. Correct your form");
            return "supplier/addsupplier";
        } else {
            MMaterial mat = new MMaterial();
            /*supplierDao.findByName(name)--->return supplier/    +.getMaterial()--->returns supplied  materials
             +.getMatId() ----> returns the id of this material
            * */

            if (supplierDao.findByName(name) != null && supplierDao.findByName(name).getMaterial().getMatId() != materialId) {
                // above : if supplier exist && delivered material is not yet recorded for him ?Then add the new material
                // to his list of delivered material
                String supplierName = name;
                int supplierId = supplierDao.findByName(name).getSupplierId();


                //supplier exist already but new material
                // Todo is to check if he has already delivered this material or if it is the first time
                // If has already delivered that material, we don't need to record this supplier again.
                // if the delivered material is not in his list of material, then add the materialId to the list
                // of the material delivered by this supplier


                List<MMaterial> listDeliveredMaterial = supplierDao.findByMMaterialAndSupplierId(material, supplierId);
                //Alternative
                //List<MMaterial> listDeliveredMaterial=supplierDao.findByMatIdAndSupplierName(materialId, suppliername);
                mat.getMatId();
                listDeliveredMaterial.add(mat);
                supplier.setMaterial(mat);
                supplierDao.save(supplier);
                return "redirect:";
            }
            else {
                if (supplierDao.findByName(name) != null && supplierDao.findByName(name).getMaterial().getMatId() == materialId){
                    return "redirect:";
                }else{
                    if(supplierDao.findByName(name) == null && supplierDao.findByName(name).getMaterial().getMatId() == materialId)
                        supplier.getSupplierId();
                        supplierDao.save(supplier);
                        return "redirect:";
                }
            }}}}







