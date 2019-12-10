package org.launchcode.dynamicinventory.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {
/*    @Autowired
    private ProductDao productDao;

    @Autowired
    private VendorDao vendorDao;
    @Autowired
    private Vendor_ProductDao vendor_productDao;

    @Autowired
    private WarehouseDao warehouseDao;

    @Autowired
    private Vendor_Product_WarehouseDao vendor_product_warehouseDao;*/

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("title", "WELCOME TO THE DYNAMIC INVENTORY MANAGEMENT SYSTEM");
        return "home/home";
    }
}
}
