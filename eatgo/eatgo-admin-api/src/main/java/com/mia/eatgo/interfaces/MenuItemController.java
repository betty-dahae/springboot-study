package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.MenuItemService;
import com.mia.eatgo.domain.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuItemController {

    @Autowired
    MenuItemService menuItemService;

    @PatchMapping("/restaurants/{restaurantId}/menuitems")
    public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems){
        menuItemService.bulkUpdate(restaurantId, menuItems);
        return "[]";
    }

}
