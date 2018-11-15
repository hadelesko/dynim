package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "flow/index";
    }


}
