package org.launchcode.dynamicinventory.controllers;

/*import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.data.FlowDao;
import org.launchcode.dynamicinventory.data.LocDao;
import org.launchcode.dynamicinventory.data.MatDao;
import org.launchcode.dynamicinventory.data.SupplierDao;*/
import org.launchcode.dynamicinventory.models.Flow;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.data.FlowDao;
import org.launchcode.dynamicinventory.models.data.LocDao;
import org.launchcode.dynamicinventory.models.data.MatDao;
import org.launchcode.dynamicinventory.models.data.SupplierDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MaterialController {
   @Autowired
   private MatDao matDao;

   @Autowired
   private FlowDao flowDao;

   @Autowired
   private LocDao locDao;

   @Autowired
   private SupplierDao supplierDao;


    @RequestMapping(value="")
    public String index(Model model) {
        model.addAttribute("title", "Warehouse management");
        model.addAttribute("materials", matDao.findAll());
        return "material/index";
    }

    @RequestMapping(value="reception", method = RequestMethod.GET)
    public String displayaddmatflow(Model model) {

        model.addAttribute("title", "Add the new flow of the material");
        model.addAttribute("material", new MMaterial());

        return "material/reception";
    }


    @RequestMapping(value = "reception", method = RequestMethod.POST)
    public String processaddmaterial(Model model,
                                     @ModelAttribute @Valid MMaterial material,
                                     Errors errors, @RequestParam("stock") double stock,
                                     @RequestParam String matName) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Insert a new Material");

            return "material/reception";
        } else {

            Flow entrymaterial=new Flow();

            // Registration of the Flow
            ///(matDao.findByMatName(matName)).getMatId(); // request the id of the material
            entrymaterial.setMaterial(matDao.findByMatName(matName));
            entrymaterial.setFlowQuantity(stock);
            entrymaterial.setName("reception");
            entrymaterial.getFlowId();
            flowDao.save(entrymaterial);

            if (matDao.findByMatName(matName) != null) { //it was checked that the
                // material with the name matName exists so the only things to do here is
                //to update the available quantity

                double newstocko = matDao.findByMatName(matName).getStock() + stock;
                matDao.findByMatName(matName).setStock(newstocko);
                matDao.save(matDao.findByMatName(matName));


                return "redirect:";

            }
            else {
                //New material that is at the first time in the database
                //Obtain the id for the new material that will help to save it into the database
                material.setMatId(material.getMatId());
                //save the new material
                matDao.save(material);
                return "redirect:";
            }
        }
    }


}
