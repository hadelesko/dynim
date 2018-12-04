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
import java.util.List;
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

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Warehouse management");
        model.addAttribute("fournisseurs", fournisseurDao.findAll());
        return "fournisseur/index";
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
    @RequestMapping(value = "{fournisseurId}")
    public String editfournisseur(Model model, @PathVariable int fournisseurId){
        Fournisseur fournisseur=fournisseurDao.findByFournisseurId(fournisseurId);
        model.addAttribute("title", "Result of the serach of the fournisseur with id= "+fournisseurId);
        model.addAttribute("fournisseur",fournisseur);
        return "fournisseur/editFournisseur";
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
}



