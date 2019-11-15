package com.mia.eatgo.domain;

public class Restaurant {

    private  String name;
    private String address;

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String  getName() {
        return this.name;
    }

    public Object getInformation() {
        return this.name + " in " + this.address;
    }
}
