package com.mia.eatgo.interfaces;

import com.mia.eatgo.domain.Restaurant;
import com.mia.eatgo.domain.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantRepository repository;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        /*
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant("mia", "Vancouver", 1004L);
        restaurants.add(restaurant);*/

        List<Restaurant> restaurants = repository.findAll();
        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    public Restaurant detail(@PathVariable Long id){
/*        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("mia", "Vancouver", 1004L) );
        restaurants.add(new Restaurant("bam", "Vancouver", 2020L) );
        List<Restaurant> restaurants = repository.findAll();
        Restaurant restaurant = restaurants.stream()
                .filter(r-> r.getId().equals(id))
                .findFirst()
                .orElse(null);*/

        Restaurant restaurant = repository.findById(id);
        return restaurant;
    }
}
