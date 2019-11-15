package com.mia.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRepository {

    List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepository() {
        restaurants.add(new Restaurant("mia", "Vancouver", 1004L) );
        restaurants.add(new Restaurant("bam", "Vancouver", 2020L) );
    }

    public List<Restaurant> findAll(){
        return restaurants;
    }

    public Restaurant findById(Long id) {
        Restaurant restaurant = restaurants.stream()
                .filter(r-> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        return restaurant;
    }
}
