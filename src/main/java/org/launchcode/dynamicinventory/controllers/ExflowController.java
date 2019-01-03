package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.Eflow;
import org.launchcode.dynamicinventory.models.Fournisseur;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = {"id={flowId}"})
    public String showbyId(Model model, @PathVariable int flowId) {
        List<Eflow>eflows=new ArrayList<>();
        String title="";
        if(exflowDao.findById(flowId)==null){
            title="Result of the search:No flow found with the id = "+flowId;
            model.addAttribute("title",title);
        }else{
            title="Result of the search of the flow with id = "+flowId+ " is the following";
            eflows.add(exflowDao.findById(flowId));
            model.addAttribute("title",title);
        }
        //model.addAttribute("title",title);
        model.addAttribute("eflows", eflows);
        return "exflow/index";
    }

    @RequestMapping(value = {"material/id={materialId}"})
    public String showbyMaterial(Model model, @PathVariable int materialId) {
        List<Eflow>eflows=new ArrayList<>();
        String title="";
        MMaterial material=matDao.findByMatId(materialId);
        if(exflowDao.findByMaterial(material).size()==0){
            title="Result of the search: No flow found with the materialId = "+materialId;
            model.addAttribute("title",title);
        }else{
            title="Result of the search of the flow with materialId = "+materialId+ " is the following";
            eflows.addAll(exflowDao.findByMaterial(material));
            model.addAttribute("title",title);
        }
        //model.addAttribute("title",title);
        model.addAttribute("eflows", eflows);
        return "exflow/index";
    }
    @RequestMapping(value = {"material/name={materialName}"})
    public String showbyMaterialName(Model model, @PathVariable String materialName) {
        List<Eflow>eflows=new ArrayList<>();
        String title="";
        MMaterial material=matDao.findByMatName(materialName);
        if(exflowDao.findByMaterial(material).size()==0){
            title="Result of the search: No flow found with the materialId = "+materialName;
            model.addAttribute("title",title);
        }else{
            title="Result of the search of the flow with materialId = "+materialName+ " is the following";
            eflows.addAll(exflowDao.findByMaterial(material));
            model.addAttribute("title",title);
        }
        //model.addAttribute("title",title);
        model.addAttribute("eflows", eflows);
        return "exflow/index";
    }

    @RequestMapping(value = {"fournisseur/id={fournisseurId}"})
    public String showbyFournisseur(Model model, @PathVariable int fournisseurId) {
        List<Eflow>eflows=new ArrayList<>();
        String title="";
        Fournisseur fournisseur=fournisseurDao.findByFournisseurId(fournisseurId);
        if(exflowDao.findByFournisseur(fournisseur).size()==0){
            title="Result of the search: No flow found with the fournisseur Id = "+fournisseurId;
            model.addAttribute("title",title);
        }else{
            title="Result of the search of the flow with fournisseur Id = "+fournisseurId+ " is the following";
            eflows.addAll(exflowDao.findByFournisseur(fournisseur));
            model.addAttribute("title",title);
        }
        //model.addAttribute("title",title);
        model.addAttribute("eflows", eflows);
        return "exflow/index";
    }
    @RequestMapping(value = {"fournisseur/name={fournisseurName}"})
    public String showbyFournisseurName(Model model, @PathVariable String fournisseurName) {
        List<Eflow>eflows=new ArrayList<>();
        String title="";
        Fournisseur fournisseur=fournisseurDao.findByName(fournisseurName);
        if(exflowDao.findByFournisseur(fournisseur).size()==0){
            title="Result of the search: No flow found with the fournisseur name = "+fournisseurName;
            model.addAttribute("title",title);
        }else{
            title="Result of the search of the flow with fournisseur name = "+fournisseurName+ " is the following";
            eflows.addAll(exflowDao.findByFournisseur(fournisseur));
            model.addAttribute("title",title);
        }
        //model.addAttribute("title",title);
        model.addAttribute("eflows", eflows);
        return "exflow/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String addflow(Model model) {

    model.addAttribute("title", "Add a flow of material");

    model.addAttribute("eflow", new Eflow());
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

        MMaterial flowMaterial= matDao.findByMatId(materialId);
        Fournisseur fournisseurfortheflow=fournisseurDao.findByFournisseurId(fournisseurId);

        eflow.setMaterial(flowMaterial);
        eflow.setFournisseur(fournisseurfortheflow);
        //flowMaterial.getEflows().add(eflow); // illegal here

        List<Eflow> eflowForThisMaterial=new ArrayList<>();
        eflowForThisMaterial.addAll(flowMaterial.getEflows());

        List<Fournisseur>fournisseursforflowedmaterial=new ArrayList<>();
                //flowMaterial.getFournisseurs().contains(fournisseurfortheflow)?
                //flowMaterial.getFournisseurs():;
        fournisseursforflowedmaterial.addAll(flowMaterial.getFournisseurs());

        model.addAttribute("material", flowMaterial);
        model.addAttribute("fournisseur",fournisseurfortheflow);

        //model.addAttribute("fournisseurId",eflow.getFournisseur());
        //model.addAttribute("materialId",eflow.getMaterial());

        if(errors.hasErrors()){
            model.addAttribute("title", "Add a correct flow. Make sure to add the required field");
            return "redirect:add";
        }else{

        //matDao.findByMatId(materialId).setStock(matDao.findByMatId(materialId).getStock()+eflow.getQuantityflow());




            //int eflowId=eflow.getId();
            //eflow.setId(eflowId);
            exflowDao.save(eflow);

            eflowForThisMaterial.add(eflow);

            fournisseurDao.findByFournisseurId(fournisseurId).getEflows().add(eflow);
            fournisseurDao.findByFournisseurId(fournisseurId).getMaterials().add(flowMaterial);

        double oldstock=matDao.findByMatId(materialId).getStock();
        double newstock=oldstock+eflow.getQuantityflow();
        //matDao.findByMatId(materialId).setStock(newstock);

        //MMaterial materiall=matDao.findByMatId(materialId);
            matDao.findByMatId(materialId).setStock(newstock);
            matDao.findByMatId(materialId).setEflows(eflowForThisMaterial);

           // matDao.findByMatId(materialId).getFournisseurs().add(fournisseurfortheflow);

            fournisseursforflowedmaterial.add(fournisseurfortheflow);
            matDao.findByMatId(materialId).setFournisseurs(fournisseursforflowedmaterial);

        //materiall.setStock(newstock);
        //materiall.getEflows().add(eflow);
        MMaterial materiall=matDao.findByMatId(materialId);
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

