package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.Eflow;
import org.launchcode.dynamicinventory.models.Fournisseur;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value="eflow")
public class ExflowController {
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
        model.addAttribute("title", "List of flows in the warehouse ");
        model.addAttribute("eflows", exflowDao.findAll());
        return "exflow/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String addflow(Model model) {

    model.addAttribute("title", "Add a flow of material");

    model.addAttribute("eflow",new Eflow());
    model.addAttribute("fournisseurs",fournisseurDao.findAll());
    model.addAttribute("materials", matDao.findAll());
    model.addAttribute(new Fournisseur());
    model.addAttribute(new MMaterial());
    //model.addAttribute(new Location());

    model.addAttribute("notExistSupplierName","Unknown");
    model.addAttribute("notExistMaterialName","Unknown");

    return "exflow/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addflowprocess(@ModelAttribute @Valid Eflow eflow, Errors errors,
                              @RequestParam("materialId") int materialId,
                              @RequestParam("fournisseurId") int fournisseurId, Model model) {

        MMaterial flowMaterial= matDao.findOne(materialId);
        Fournisseur fournisseurfortheflow=fournisseurDao.findOne(fournisseurId);

        eflow.setMaterial(flowMaterial);
        eflow.setFournisseur(fournisseurfortheflow);
        flowMaterial.getEflows().add(eflow);

        List<Eflow> eflowForThisMaterial=flowMaterial.getEflows();


        model.addAttribute("material", flowMaterial);
        model.addAttribute("fournisseur",fournisseurfortheflow);

        model.addAttribute("fournisseurId",eflow.getFournisseur());
        model.addAttribute("materialId",eflow.getMaterial());

        if(errors.hasErrors()){
            model.addAttribute("title", "Add a correct flow. Make sure to add the required field");
            return "redirect:add";
        }else{


        //matDao.findByMatId(materialId).setStock(matDao.findByMatId(materialId).getStock()+eflow.getQuantityflow());

         eflowForThisMaterial.add(eflow);
         eflow.setMaterial(flowMaterial);
            int eflowId=eflow.getId();
            eflow.setId(eflowId);
            exflowDao.save(eflow);

        double oldstock=matDao.findOne(materialId).getStock();
        double newstock=oldstock+eflow.getQuantityflow();
        //matDao.findByMatId(materialId).setStock(newstock);

        MMaterial materiall=matDao.findByMatId(materialId);
        materiall.setStock(newstock);

        matDao.save(materiall);



        return "redirect:/material/";
    }

}
@RequestMapping(value="update")
public String updateflowWithoutdate(){
        List<Eflow>eflows=new ArrayList<>();
                ;
        for(Eflow eflow:exflowDao.findAll()){
            if(eflow.getDate()==null){
                eflow.setDate(new Date());
                eflows.add(eflow);
            }else{
                Date date=eflow.getDate();
                eflow.setDate(date);
                eflows.add(eflow);
            }
        }
        exflowDao.save(eflows);
        return "redirect:";
}

}

