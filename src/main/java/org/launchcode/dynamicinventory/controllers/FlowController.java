package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.data.FlowDao;
import org.launchcode.dynamicinventory.models.data.LocDao;
import org.launchcode.dynamicinventory.models.data.MatDao;
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

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "List of flows in the warehouse ");
        model.addAttribute("flows", flowDao.findAll());
        return "flow/index";
    }

}
