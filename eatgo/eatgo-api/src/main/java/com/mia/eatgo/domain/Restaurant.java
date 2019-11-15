package com.mia.eatgo.domain;

public class Restaurant {

    private  String name;
    private String address;
    private Long id;

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
}
