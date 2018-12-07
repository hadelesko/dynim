package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.Flow;
import org.launchcode.dynamicinventory.models.Location;
import org.launchcode.dynamicinventory.models.MMaterial;
import org.launchcode.dynamicinventory.models.data.FlowDao;
import org.launchcode.dynamicinventory.models.data.LocDao;
import org.launchcode.dynamicinventory.models.data.MatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "location")
public class LocationController {
    @Autowired
    private LocDao locDao;

    @Autowired
    private FlowDao flowDao;

    @Autowired
    private MatDao matDao;

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



    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addlocationdisplayform(Model model) {
        model.addAttribute("title", "Create new location for material storage in the warehouse ");
        model.addAttribute("location",new Location());
        model.addAttribute(new MMaterial());
        model.addAttribute(new Flow());
        model.addAttribute("materials",matDao.findAll());

        model.addAttribute("availableLocations",locDao.findAll());

        return "location/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addlocationproceedform(Model model, @ModelAttribute @Valid Location location, Errors errors,
                                         @RequestParam("name") String name,
                                         @RequestParam("locationStock") double locationStock,
                                         @RequestParam("materialId") int materialId) {

        MMaterial material = matDao.findByMatId(materialId);
        location.setMaterial(material);
        model.addAttribute("material", material);
        //model.addAttribute("materialId", location.getMaterial());

        if (errors.hasErrors()) {
            model.addAttribute("title", "Make sure the required fields are not empty");
            return "location/add";
        } else {

            if (locDao.findByName(location.getName()) == null) { //The location does not exist before--> Save it

                locDao.save(location);
                //update the list of the locations where this material is stored and save the updated material

                material.getLocations().add(location);
                matDao.save(material);
                return "location/editLocation";
            } else { //The location exists already. Test if the material stored on that location is the same material as
                // new to be located
                if(locDao.findByName(location.getName()).getMaterial().getMatId()==location.getMaterial().getMatId()){
                    // update the material stock at this location with new quantity (of the part) of the delivered material
                    // update location : entryStock + existingStock
                    double stockBeforeEntryLocation=locDao.findByName(location.getName()).getLocationStock();
                    double stockAfterEntryLocation=stockBeforeEntryLocation+locationStock;
                    // Copy of the location in the new variable, update and save it
                    Location tobeUpdatedLocation=locDao.findByName(location.getName());

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
}