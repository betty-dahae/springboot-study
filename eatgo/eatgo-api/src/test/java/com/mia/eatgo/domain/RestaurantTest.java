package com.mia.eatgo.domain;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
public class RestaurantTest {

    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant("mia");
        assertThat(restaurant.getName(), is("mia"));
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant("mia", "Vancouver");
        assertThat(restaurant.getInformation(), is("mia in Vancouver"));
    }
}