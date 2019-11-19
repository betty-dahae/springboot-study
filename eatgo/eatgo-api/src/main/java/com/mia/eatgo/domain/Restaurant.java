package com.mia.eatgo.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Restaurant {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private  String name;
    @NotEmpty
    private String address;

    @Transient //임시로 통과
    private List<MenuItem> menuItems = new ArrayList<>();

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
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void updateInformation(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
