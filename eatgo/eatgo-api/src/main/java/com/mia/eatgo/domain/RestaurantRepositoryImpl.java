package com.mia.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component//IOC를 위한 주석
public class RestaurantRepositoryImpl implements RestaurantRepository {

    //extract interface
    //1. right click on mouse
    //2. refactor > extract > interface
    List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImpl() {
        restaurants.add(new Restaurant("mia", "Vancouver", 1004L) );
        restaurants.add(new Restaurant("bam", "Vancouver", 2020L) );
    }

    @Override
    public List<Restaurant> findAll(){
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        Restaurant restaurant = restaurants.stream()
                .filter(r-> r.getId().equals(id))
                .findFirst()
                .orElse(null);
        return restaurant;
    }
}
