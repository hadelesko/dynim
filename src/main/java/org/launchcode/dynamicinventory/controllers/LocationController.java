package org.launchcode.dynamicinventory.controllers;

import org.launchcode.dynamicinventory.models.Location;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "location")
public class LocationController {
    @Autowired
    private LocDao locationDao;

    @Autowired
    private FlowDao flowDao;

    @Autowired
    private MatDao matDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "List of locations in the warehouse ");
        model.addAttribute("locations", locationDao.findAll());
        return "location/index";
    }

    @RequestMapping(value="search", method =RequestMethod.GET)
    public String search(Model model){
        model.addAttribute("title","The location information");
        return "location/searchLocation";
    }
    @RequestMapping(value="search", method =RequestMethod.POST)
    public String search(Model model, @RequestParam("locationName") String name,
                         @RequestParam Map<String,Integer>vars) {

        int locationId = vars.get("locationId");

        int stock = vars.get("locationStock");
        List<Location> locations=new ArrayList<>();
        if (name != null && vars.get("locationId") == null && vars.get("locationStock") == null) {
            /*Here the name of the location is given.The result of this search is only one location and can be done by
             * Location location findOne(id). As list can be empty or contain one or more value, this result can be used as list*/
            locations = locationDao.findByName(name);
            //return "location/searchresult";
        }
        if (name == null && vars.get("locationId") != null) {
            /*Here the id of the location is given. The result of this search is only one location and can be done by
             * Location location findOne(id). As list can be empty or contain one or more value, this result can be used as list*/
            locations = locationDao.findByLocationId(locationId);
            //return "location/searchresult";
        }
        if (vars.get("locationStock") != null) {
            /*The result of this search is a list of location with specified available stock of material can be search gives the list
             *This result can be used to know which place or locations are free or will be soon free for the relocation new reception
             * of material or need to be refilled*/
            locations = locationDao.findByLocationStock(stock);
            //return "location/searchresult";
        }
        model.addAttribute("locations", locations);
        return "location/editLocation";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addlocationdisplayform(Model model) {
        model.addAttribute("title", "Create new location for material storage in the warehouse ");
        model.addAttribute(new Location());


        return "location/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addlocationproceedform(Model model, @ModelAttribute @Valid Location location, Errors errors,
                                         @RequestParam("name") String name) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "Make sure the required fields are not empty");
            return "location/add";
        }
/*        for (Location newloc : locationDao.findAll()) {
            if (newloc.getName()!=name) {
                return "redirect:";
            } else {
                location.getLocationId();
                location.setLocationId(location.getLocationId());
                locationDao.save(location);
                return "location/editLocation";
            }
        }}}*/
        if (locationDao.findByName(name) == null) {
            location.getLocationId();
            //location.setLocationId(location.getLocationId());
            locationDao.save(location);
            //return "location/editLocation";
        }
        //return "redirect:";
        return "location/editLocation";
    }}