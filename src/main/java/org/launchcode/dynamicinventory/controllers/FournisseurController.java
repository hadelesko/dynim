package org.launchcode.dynamicinventory.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.dynamicinventory.models.*;
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
        return "fournisseur/searchFournisseur";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String editsearch(Model model, HttpServletRequest request) {
        String searchTerm = request.getParameter("searchTerm");
        model.addAttribute("title", "The result of your search ");
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("material", fournisseurDao.findByName(searchTerm));
        return "fournisseur/edit";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addFournisseurForm(Model model) {
        model.addAttribute("title", "Add a new Fournisseur to the list of the Fournisseurs");
        model.addAttribute(new Fournisseur());
        model.addAttribute("fournisseurs", fournisseurDao.findAll());
        //model.addAttribute("actuellistofmaterials", matDao.findAll());
        //model.addAttribute(new MMaterial());
        return "fournisseur/addFournisseur";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addfournisseurformprocess(Model model, @ModelAttribute @Valid Fournisseur fournisseur, @RequestParam String name, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "make sure to fill th required fields. Correct your form");
            return "fournisseur/addFournisseur";
        } else {
            List<Fournisseur> presentlistfournisseurs = fournisseurDao.findAll();
            for (Fournisseur oldfournisseur : presentlistfournisseurs) {
                if (oldfournisseur.getName().equalsIgnoreCase(name)) {
                    model.addAttribute("fournisseurexistsalredy", fournisseurDao.findByName(name)); //this will be prompted by javascript alert
                    return "redirect:search";
                }
            }
            fournisseurDao.save(fournisseur);
            return "redirect:";
        }

    }
}



