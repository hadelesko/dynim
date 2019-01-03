package org.launchcode.dynamicinventory.controllers;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="fournisseur")
public class FournisseurController {

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

    @Autowired
    private FournisseurDao fournisseurDao;
    @Autowired
    private ExflowDao exflowDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Warehouse management");
        model.addAttribute("fournisseurs", fournisseurDao.findAll());
        model.addAttribute("eflows",exflowDao.findAll());
/*        model.addAttribute("materials", matDao.findAll());
        boolean materialIsDeleveredByThisfournisseur=false;
        List<MMaterial>listMaterialBythisFournisseur=new ArrayList<>();
        for(Fournisseur fournisseur:fournisseurDao.findAll()){
            for(MMaterial material:matDao.findAll()){
                if(material.getFournisseurs().contains(fournisseur)==true){
                    materialIsDeleveredByThisfournisseur=true;
                    listMaterialBythisFournisseur.add(material);
                }else{
                    materialIsDeleveredByThisfournisseur=false;
                }
            }
        }
        model.addAttribute("eflows", exflowDao.findAll());
        model.addAttribute("listMaterialBythisFournisseur",listMaterialBythisFournisseur);
        model.addAttribute("materialIsDeleveredByThisfournisseur", materialIsDeleveredByThisfournisseur);*/
        return "fournisseur/index";
    }

    @RequestMapping(value = "id={fournisseurId}")
    public String schowfournisseur(Model model, @PathVariable int fournisseurId){
        List<Fournisseur>foundListOfFournisseur=new ArrayList<>();
        Fournisseur foundFournisseur=fournisseurDao.findByFournisseurId(fournisseurId);
        boolean existThisournisseur;

        if(foundFournisseur==null){
            existThisournisseur=false;
            model.addAttribute("existFournisseur", existThisournisseur);
        }else{
            existThisournisseur=true;
            model.addAttribute("existFournisseur", existThisournisseur);
            foundListOfFournisseur.add(foundFournisseur);
        }
        model.addAttribute("foundListOfFournisseur", foundListOfFournisseur);
        model.addAttribute("foundFournisseur",foundFournisseur);
        model.addAttribute("eflows", exflowDao.findAll());
        model.addAttribute("title", "Result of the fournisseur with id = "+fournisseurId);
        return "fournisseur/singleFournisseurShow";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("title", "Warehouse management: Search of the Supplier");
        model.addAttribute("fournisseurs",fournisseurDao.findAll());
        return "fournisseur/searchFournisseur";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String editsearch(Model model, @RequestParam("searchTerm") String searchTerm,
                             @RequestParam("searchValue") String searchValue) {

        //String searchTerm = request.getParameter("searchTerm");
        //String searchValue = request.getParameter("searchValue");
        //String searchValue=request.getParameter("searchValue");

        // Control of the entered values
        if(searchTerm.equalsIgnoreCase("id")) {

            boolean isnumeric = true;

            try {
                int id = Integer.parseInt(searchValue);

            } catch (NumberFormatException e) {
                isnumeric = false;
            }
            //List<Fournisseur>fournisseurs= (List<Fournisseur>) fournisseurDao.findAll();
            if (isnumeric && fournisseurDao.findByFournisseurId(Integer.parseInt(searchValue)) != null) {
                model.addAttribute("searchedFournisseur", fournisseurDao.findByFournisseurId(Integer.parseInt(searchValue)));
            }

            model.addAttribute("title", "The result of your search for the fournisseur with "+searchTerm+" = "+ searchValue );
            //model.addAttribute("searchTerm", searchTerm);

            //model.addAttribute("fournisseur", fournisseurDao.findByName(searchTerm));
            return "fournisseur/editFournisseur";
            //return "fournisseur/edit";
        }
        else{
                if(searchTerm.equalsIgnoreCase("name") && fournisseurDao.findByName(searchValue)!=null){
                    model.addAttribute("searchedFournisseur",fournisseurDao.findByName(searchValue));
                    }
                    else{
                        if(searchTerm.equalsIgnoreCase("email") && fournisseurDao.findByEmail(searchValue)!=null){
                            model.addAttribute("searchedFournisseur",fournisseurDao.findByEmail(searchValue));
                    }

                }
            model.addAttribute("title", "The result of your search for the fournisseur with "+searchTerm+" = "+ searchValue );
            return "fournisseur/editFournisseur";
        }
       /* model.addAttribute("title", "The result of your search for the fournisseur with "+searchTerm+" = "+ searchValue );
        model.addAttribute("searchTerm", searchTerm);

        model.addAttribute("fournisseur", fournisseurDao.findByName(searchTerm));
        return "fournisseur/edit";*/
    }
/*    @RequestMapping(value = "id={fournisseurId}")
    public String editfournisseur(Model model, @PathVariable int fournisseurId){
        Fournisseur fournisseur=fournisseurDao.findByFournisseurId(fournisseurId);
        model.addAttribute("title", "Result of the serach of the fournisseur with id= "+fournisseurId);
        model.addAttribute("fournisseur",fournisseur);
        return "fournisseur/editFournisseur";
    }*/

    @RequestMapping(value = {"name={fournisseurName}"})
    public String showbyFournisseurName(Model model, @PathVariable String fournisseurName) {
        List<Fournisseur>fournisseurs=new ArrayList<>();
        String title="";
        Fournisseur fournisseur=fournisseurDao.findByName(fournisseurName);
        if(fournisseurDao.findByName(fournisseurName)==null){
            title="Result of the search: No fournisseur found with name = "+fournisseurName;
            //model.addAttribute("title",title);
        }else{
            title="Result of the search of the fournisseur with name = "+fournisseurName+ " is the following";
            fournisseurs.add(fournisseur);
            //model.addAttribute("title",title);
        }
        model.addAttribute("title",title);
        model.addAttribute("fournisseurs", fournisseurs);
        return "fournisseur/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addFournisseurForm(Model model) {
        model.addAttribute("title", "Add a new Fournisseur to the list of the Fournisseurs");
        model.addAttribute(new Fournisseur());
        model.addAttribute("fournisseurs", fournisseurDao.findAll());
        model.addAttribute("materials", matDao.findAll());

        //model.addAttribute("actuellistofmaterials", matDao.findAll());
        //model.addAttribute(new MMaterial());
        return "fournisseur/addFournisseur";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addfournisseurformprocess(Model model, @ModelAttribute @Valid Fournisseur fournisseur,
                                            @RequestParam("materialId") int materialId,
                                            @RequestParam String name, Errors errors) {

        MMaterial material=matDao.findOne(materialId);
        List<MMaterial>providedMaterials=new ArrayList<>();

        providedMaterials.add(material);
        fournisseur.setMaterials(providedMaterials);

        if (errors.hasErrors()) {
            model.addAttribute("title", "make sure to fill th required fields. Correct your form");
            return "fournisseur/addFournisseur";
        } else {

            List<Fournisseur> presentlistfournisseurs = (List<Fournisseur>) fournisseurDao.findAll();
            //List<MMaterial>listMaterialForThisFounisseur=fournisseur.getMaterials();
            for (Fournisseur oldfournisseur : presentlistfournisseurs) {
                if (oldfournisseur.getName().equalsIgnoreCase(name)) {
                    model.addAttribute("fournisseurexistsalredy", fournisseurDao.findByName(name)); //this will be prompted by javascript alert
                    return "redirect:search";
                }
            }
            //Update list material-fournisseur
            List<Fournisseur>thismaterialFournisseurs=material.getFournisseurs();

            //material.getFournisseurs().add(fournisseur);

            thismaterialFournisseurs.add(fournisseur);  //Add this supplier to the suppliers of this material
            matDao.findOne(materialId).setFournisseurs(thismaterialFournisseurs);
            fournisseurDao.save(fournisseur);

            return "redirect:";
        }

    }
    @RequestMapping(value="statistics")
    public String fournisseurstatistics(Model model) {

        List<HashMap<String,List<HashMap<String,String>>>>allMaterialResume=new ArrayList<>();

        for (MMaterial material: matDao.findAll()){

            HashMap<String,List<HashMap<String,String>>> resumeforThisMaterial=new HashMap<>();
            List<HashMap<String, String>>allfournisseursResume=new ArrayList<>();
            for(Eflow eflow:material.getEflows()){
                //int countEflowsOfThisMaterial=material.getEflows().size();
                //int countfournisseursForThisMaterial=material.getFournisseurs().size();
                for(Fournisseur f:material.getFournisseurs()){
                    HashMap<String,String> resumeforThisfournisseur=new HashMap<>();
                    resumeforThisfournisseur.put("fournisseur_Id",Integer.toString(f.getFournisseurId()));
                    resumeforThisfournisseur.put("name", f.getName());
                    resumeforThisfournisseur.put("numberOfMaterials",Integer.toString(f.getMaterials().size()));
                    resumeforThisfournisseur.put("numberOfFlows",Integer.toString(f.getEflows().size()));
                    int fournisseurFlowFrequencyForThisMaterial=0;
                    double quantityOfThisMaterialSuppliedByThisFournisseur=0;
                    if(eflow.getFournisseur()==f){
                        fournisseurFlowFrequencyForThisMaterial+=1;
                        quantityOfThisMaterialSuppliedByThisFournisseur +=eflow.getQuantityflow();
                    }
                    resumeforThisfournisseur.put("frequency ","material '"
                            + material.getMatName()+"' was delivered|"
                            +fournisseurFlowFrequencyForThisMaterial
                            +" over "+ material.getEflows().size()
                            +" time(s) by fournisseur '"+f.getName()+"' |total delivered| "
                            + quantityOfThisMaterialSuppliedByThisFournisseur);
                    allfournisseursResume.add(resumeforThisfournisseur);

                }

            }
            resumeforThisMaterial.put(material.getMatName(),allfournisseursResume);
            Map<String, Double> locationsForThisMaterial=new HashMap<>();
            material.getLocations().forEach(location->locationsForThisMaterial.put(location.getName(),location.getLocationStock()));
           // resumeforThisMaterial.put("locations",locationsForThisMaterial.forEach((singlelocation,stock)->(singlelocation));
            //resumeforThisMaterial.put("fournisseurs",allfournisseursResume.toString());
            //model.addAttribute("resumeforThisMaterial", resumeforThisMaterial);
            allMaterialResume.add(resumeforThisMaterial);
       }
        model.addAttribute("allMaterialResume", allMaterialResume);
        model.addAttribute("title", "Resume");
        return "fournisseur/someStatistics";
    }
}



