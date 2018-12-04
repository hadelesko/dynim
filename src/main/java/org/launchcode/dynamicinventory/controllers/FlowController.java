package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="flow")
public class FlowController {
    @Autowired
    private LocDao locDao;

    @Autowired
    private FlowDao flowDao;

    @Autowired
    private MatDao matDao;
    @Autowired
    private FournisseurDao fournisseurDao;
    @Autowired
    private ExflowDao exflowDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "List of flows in the warehouse ");
        model.addAttribute("flows", flowDao.findAll());
        return "flow/index";
    }



}
