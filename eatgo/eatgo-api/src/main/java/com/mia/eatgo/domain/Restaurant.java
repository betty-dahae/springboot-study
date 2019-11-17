package com.mia.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    private  String name;
    private String address;
    private Long id;
    private List<MenuItem> menuItems = new ArrayList<>();

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Restaurant(String name, String address, Long id) {
        this.name = name;
        this.address = address;
        this.id = id;
    }


    public String  getName() {
        return this.name;
    }

    public String getAddress() {
        return address;
    }

    public Long getId() {
        return id;
    }

    public Object getInformation() {
        return this.name + " in " + this.address;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for(MenuItem menuItem : menuItems){
            addMenuItem(menuItem);
        }
    }
}
