package org.launchcode.dynamicinventory.controllers;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.MaterialOrder;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "order")
public class InternOderController {

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
        model.addAttribute("title", "Warehouse order management");
        model.addAttribute("materialorders", internOrderDao.findAll());
        return "materialorder/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String editorder(Model model) {
        model.addAttribute("title", " Intern Material Order management: find your material and add the quantity");

        model.addAttribute("materialorders", internOrderDao.findAll());
        model.addAttribute("materials", matDao.findAll());
        model.addAttribute("materialOrder", new MaterialOrder());
        return "materialorder/order";
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String editorderproceed(Model model, @ModelAttribute @Valid MaterialOrder materialOrder,
                                   Errors errors, HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Error! make a valid order");

            return "materialorder/order";
        }

        model.addAttribute("title", " Intern Material Order management: List of the ordered material");

        List<MMaterial> materials = matDao.findAll();
        model.addAttribute("materials", matDao.findAll());
        internOrderDao.save(materialOrder);

        return "redirect:";
    }

    @RequestMapping(value = "batches")
    public String batchoftoday(Model model) {
        /*Date date=new Date();
        int theday=date.getDay();
        int themonth=date.getMonth();
        int theyear=date.getYear();*/
        //String strdate=(toString(theday)+"-"+toString(themonth)+"-"+toString(theyear));

        model.addAttribute("todaybatch", internOrderDao.findByDate(new Date()));
        model.addAttribute("title", " Intern Material Order management: List of orders of the date" + new Date());
        return "materialorder/todaybatch";
    }

}
