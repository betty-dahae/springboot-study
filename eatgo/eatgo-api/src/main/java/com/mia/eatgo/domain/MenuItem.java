package com.mia.eatgo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MenuItem {
    @Id
    @GeneratedValue
    private Long id;

    private Long restaurantId;
    private String menu;
    public MenuItem(String menu) {
        this.menu = menu;
    }

    public String getMenu() {
        return menu;
    }
}
