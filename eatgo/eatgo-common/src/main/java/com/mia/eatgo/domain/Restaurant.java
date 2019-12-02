package com.mia.eatgo.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private Long categoryId;

    @NotEmpty
    private  String name;
    @NotEmpty
    private String address;

    @Transient //임시로 통과
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems;


    @Transient //임시로 통과
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Review> reviews;

    public Object getInformation() {
        return this.name + " in " + this.address;
    }

    public void updateInformation(String name, String address, Long categoryId) {
        this.name = name;
        this.address = address;
        this.categoryId = categoryId;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = new ArrayList<>(menuItems);
    }

    public void setReviews(List<Review> reviews){
        this.reviews = new ArrayList<>(reviews);
    }
}
