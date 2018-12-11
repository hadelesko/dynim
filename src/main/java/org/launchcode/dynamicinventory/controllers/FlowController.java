package org.launchcode.dynamicinventory.controllers;


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
@RequestMapping(value="flow")
public class FlowController {
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
        model.addAttribute("flows", flowDao.findAll());
        return "flow/index";
    }
    @RequestMapping(value="add",method = RequestMethod.GET)
    public String addFlow(Model model){
        model.addAttribute("title", "Add a flow of the material");
        model.addAttribute(new Flow());

        model.addAttribute("suppliers", supplierDao.findAll());
        model.addAttribute("locations", locDao.findAll());
        model.addAttribute("materials", matDao.findAll());
        model.addAttribute("fournisseurs", fournisseurDao.findAll());
        return "flow/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String addflowprocess(@ModelAttribute @Valid Flow flow, Errors errors,
                                 @RequestParam("materialId") int materialId,
                                 @RequestParam("supplierId") int supplierId,
                                 @RequestParam("locationId") int locationId,
                                 Model model) {

        MMaterial flowMaterial= matDao.findOne(materialId);
        Supplier supplierForTheFlow=supplierDao.findOne(supplierId);
        Location location=locDao.findOne(locationId);

        model.addAttribute("supplierId",flow.getSupplier());
        model.addAttribute("materialId",flow.getMaterial());
        model.addAttribute("location",flow.getLocation());


        flow.setMaterial(flowMaterial);
        flow.setSupplier(supplierForTheFlow);
        flow.setLocation(location);

        List<Flow> flowForThisMaterial=flowMaterial.getFlows();

        model.addAttribute("material", flowMaterial);
        model.addAttribute("supplier",supplierForTheFlow);
        model.addAttribute("location", location);

        if(errors.hasErrors() ||location.getMaterial().getMatId()== materialId){
            model.addAttribute("title", "Add a correct flow. Make sure to add the required field");
            return "redirect:add";
        }else{

            int flowId=flow.getFlowId();
            flow.setFlowId(flowId);
            flowDao.save(flow);
            //matDao.findByMatId(materialId).setStock(matDao.findByMatId(materialId).getStock()+eflow.getQuantityflow());
            flowMaterial.getFlows().add(flow);
            flowForThisMaterial.add(flow);
            //flow.setMaterial(flowMaterial);


            double oldstock=matDao.findOne(materialId).getStock();
            double newstock=oldstock+flow.getFlowQuantity();
            //matDao.findByMatId(materialId).setStock(newstock);

            MMaterial materiall=matDao.findByMatId(materialId);
            materiall.setStock(newstock);
            if(materiall.getLocations().contains(location)==false){
                materiall.getLocations().add(location);
            }
            if(location.getMaterial().getMatId()== materialId)

            matDao.save(materiall);

            return "redirect:/material/";
        }

}}
