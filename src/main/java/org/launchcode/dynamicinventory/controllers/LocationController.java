package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.Flow;
import org.launchcode.dynamicinventory.models.Location;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "location")
public class LocationController {
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
        model.addAttribute("title", "List of locations in the warehouse ");
        model.addAttribute("locations", locDao.findAll());
        return "location/index";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(Model model) {
        model.addAttribute("title", "The location information");
        return "location/searchLocation";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(Model model, HttpServletRequest request) {


        Location location = new Location();
        List<Location> locations = new ArrayList<>();
        if (request.getParameter("locationName") != null && locDao.findByName(request.getParameter("locationName")) != null) {
            // && request.getParameter("locationStock") == null) {
            /*Here the name of the location is given.The result of this search is only one location and can be done by
             * Location location findOne(id). As list can be empty or contain one or more value, this result can be used as list*/

            //String locationName = request.getParameter("locationName");

            //int locationId = Integer.parseInt(request.getParameter("locationId"));

            //int stock = Integer.parseInt(request.getParameter("locationStock"));

            location = locDao.findByName(request.getParameter("locationName"));


            //model.addAttribute("locations", locations);
            //return "location/editLocation";
        } else {

            if (request.getParameter("locationId") != "" && locDao.findByLocationId(Integer.parseInt(request.getParameter("locationId"))) != null) {
                /*Here the id of the location is given. The result of this search is only one location and can be done by
                 * Location location findOne(id). As list can be empty or contain one or more value, this result can be used as list*/
                //double stock = Double.parseDouble(request.getParameter("locationStock"));
                int locationId = Integer.parseInt(request.getParameter("locationId"));
                location = locDao.findByLocationId(locationId);
                location.setName(location.getName());
                location.setLocationStock(location.getLocationStock());
                locations.add(location);
                //model.addAttribute("locations", locations);
                //return "location/editLocation";
            } else {


                if (request.getParameter("locationStock") != null) {
                    /*The result of this search is a list of location with specified available stock of material can be search gives the list
                     *This result can be used to know which place or locations are free or will be soon free for the relocation new reception
                     * of material or need to be refilled*/
                    List<Location> locationswiththisstock;
                    double stock = Double.parseDouble(request.getParameter("locationStock"));
                    if (locDao.findByLocationStock(Double.parseDouble(request.getParameter("locationStock"))) != null) {
                        locationswiththisstock = locDao.findByLocationStock(stock);
                        locations.addAll(locationswiththisstock);
                    } else {
                        locations = null;
                    }
                    model.addAttribute("locations", locations);
                    //return "location/editLocation";
                }


            }
        }
        locations.add(location);
        //model.addAttribute("location", location);
        model.addAttribute("locations", locations);
        return "location/editLocation";
    }

    @RequestMapping(value = "id={locationId}")
    public String schowMaterial(Model model, @PathVariable int locationId) {
        List<Location> foundListOfLocation = new ArrayList<>();
        Location foundLocation = locDao.findByLocationId(locationId);
        boolean existLocation;
        if (foundLocation == null) {
            existLocation = false;
            model.addAttribute("existLocation", existLocation);
        } else {
            existLocation = true;
            model.addAttribute("existLocation", existLocation);
            foundListOfLocation.add(foundLocation);
        }
        model.addAttribute("foundListOfLocation", foundListOfLocation);

        //model.addAttribute("alllocations",locDao.findAll());
        model.addAttribute("title", "Result of the location with id = " + locationId);
        return "location/singleLocationShow";
    }

    // This function aimed to close the gap between the available stock of each material and its stored quantity
    // It can come that the sum of quantities of the stored material in different places does not match the
    // total available quantity of the material in the system. THe stapler has maybe omitted to relocate the
    // material after the reception or forget to associate e new location for the new material
    @RequestMapping(value = "task")
    public String locationstask(Model model) {
        List<MMaterial> listOfMaterials = new ArrayList<>();
        listOfMaterials.addAll(matDao.findAll());
        List<String> taskListOfMaterialNeedingRelocation = new ArrayList<>();


        for (MMaterial material : listOfMaterials) {
            //get the stock of the material
            double availableStock = material.getStock();
            // Get the list of locations of this material if these exist
            List<Location> locationsForhisMaterial = locDao.findByMaterial(material);
            double locatedStockOfThisMaterial = 0;
            List<String> actuellocationsnameforthematerial = new ArrayList<>();
            for (Location location : locationsForhisMaterial) {
                //cummulation of the stock available of this material at its locations
                locatedStockOfThisMaterial += location.getLocationStock();
            }
            double tobelocatedSock = availableStock - locatedStockOfThisMaterial;

            boolean relocationNeeded = (tobelocatedSock > 0) ? true : false;
            if (relocationNeeded == true) {
                //Map<String, Double>materialAndQuantity=new HashMap<>();
                //materialAndQuantity.put(material.getMatName(),tobelocatedSock);

                for (Location el : locationsForhisMaterial) {
                    actuellocationsnameforthematerial.add(el.getName());
                }
                String newTask = material.getMatName() + ":" + tobelocatedSock + ":" + actuellocationsnameforthematerial;
                taskListOfMaterialNeedingRelocation.add(newTask);

                model.addAttribute("title", "Location needed for " + tobelocatedSock + " of the material=" + material.getMatName());

            } else {//no material to be relocated
                model.addAttribute("title", "No material to be relocated");
            }
        }
        model.addAttribute("taskListOfMaterialNeedingRelocation", taskListOfMaterialNeedingRelocation);
        return "location/taskList";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addlocationdisplayform(Model model) {
        model.addAttribute("title", "Create new location for material storage in the warehouse ");
        model.addAttribute("location", new Location());
        model.addAttribute(new MMaterial());
        model.addAttribute(new Flow());
        model.addAttribute("materials", matDao.findAll());

        model.addAttribute("availableLocations", locDao.findAll());

        return "location/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addlocationproceedform(Model model, @ModelAttribute @Valid Location location, Errors errors,
                                         @RequestParam("name") String name,
                                         @RequestParam("locationStock") double locationStock,
                                         @RequestParam("materialId") int materialId) {

        MMaterial material = matDao.findByMatId(materialId);
        List<Location> listLocforselectedmaterial = material.getLocations();
        location.setMaterial(material);
        model.addAttribute("material", material);
        //model.addAttribute("materialId", location.getMaterial());
        double locatableQuantityForTheSelectedMaterial = 0;

        //locatableQuantityForTheSelectedMaterial+=material.getLocations().forEach(loc->loc.getLocationStock());
        for (Location loc : listLocforselectedmaterial) {
            locatableQuantityForTheSelectedMaterial += loc.getLocationStock();

        }
        //model.addAttribute("locatableQuantityForTheSelectedMaterial",locatableQuantityForTheSelectedMaterial);
        if (errors.hasErrors() || locationStock > locatableQuantityForTheSelectedMaterial) {
            String quantitytostring = String.valueOf(locatableQuantityForTheSelectedMaterial);
            String title = locationStock > locatableQuantityForTheSelectedMaterial ?
                    "Error:the entered stock is greater than the available to be located(" +
                            quantitytostring + ") for the selected material " +
                            material.getMatName() + ".Please enter the stock less equals " + quantitytostring :
                    "Make sure the required fields are not empty";

            model.addAttribute("title", title);
            model.addAttribute("materials", matDao.findAll());
            return "location/add";
        } else {
/*            // Make sure that the user don't enter the a a quantity greater than the quantity
            // not located(received but not yet placed in the warehouse)
            double locatableQuantity=locatableQuantityForTheSelectedMaterial;
            if (locationStock > locatableQuantity) {
                String quantitytostring=Double.toString(locatableQuantity);
                String title="Error:the entered stock is greater than the available to be located(" +
                        quantitytostring + ") for the selected material " +
                        material.getMatName() + ".Please enter the stock less equals "+quantitytostring;
                model.addAttribute("title", title);
                model.addAttribute("materials", matDao.findAll());
                return "location/add";

            } else {*/


            if (locDao.findByName(location.getName()) == null) { //The location does not exist before--> Save it

                locDao.save(location);
                //update the list of the locations where this material is stored and save the updated material
                model.addAttribute("newlocation", location);

                List<Location> locationsforthisMaterial = new ArrayList<>();
                material.getLocations().add(location);

                locationsforthisMaterial.addAll(material.getLocations());
                material.setLocations(locationsforthisMaterial);
                matDao.save(material);
                return "location/editLocation";
            } else {
                //The location exists already. Test if the material stored on that location is the same material as
                // new to be located or placed
                if (locDao.findByName(location.getName()).getMaterial().getMatId() == location.getMaterial().getMatId()) {
                    // update the material stock at this location with new quantity (of the part) of the delivered material
                    // update location : entryStock + existingStock
                    double stockBeforeEntryLocation = locDao.findByName(location.getName()).getLocationStock();
                    double stockAfterEntryLocation = stockBeforeEntryLocation + locationStock;
                    // Copy of the location in the new variable, update and save it
                    Location tobeUpdatedLocation = locDao.findByName(location.getName());

                    tobeUpdatedLocation.setLocationStock(stockAfterEntryLocation);
                    // Save the update
                    tobeUpdatedLocation.setLocationId(tobeUpdatedLocation.getLocationId());
                    locDao.save(tobeUpdatedLocation);
                }

                return "redirect:";
                //return "location/editLocation";
            }
        }
    }

    @RequestMapping(value = "place/{materialName}", method = RequestMethod.GET)
    public String placeMaterial(Model model, @PathVariable String materialName) {
        MMaterial material = matDao.findByMatName(materialName);
        List<Location> availableLocations = material.getLocations();
        model.addAttribute("availableLocations", availableLocations);
        model.addAttribute("material", material);
        model.addAttribute("title", "Place a material '" + materialName + "'");
        return "location/place";
    }

    @RequestMapping(value = "place/{materialName}", method = RequestMethod.POST)
    public String placeMaterialpost(@RequestParam("materialName")String materialName, Model model) {



    }
}
}