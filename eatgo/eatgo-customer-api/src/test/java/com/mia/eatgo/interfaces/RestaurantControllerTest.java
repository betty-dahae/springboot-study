package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.RestaurantService;
import com.mia.eatgo.domain.MenuItem;
import com.mia.eatgo.domain.Restaurant;
import com.mia.eatgo.domain.RestaurantNotFoundException;
import com.mia.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //스프링을 이용하여 테스트 실행
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    RestaurantService restaurantService; //RestaurantController가 의존하고 있는건 restaurantService뿐
    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(Restaurant.builder().name("mia").address("Vancouver").id(1004L).build());
        given(restaurantService.getRestaurants()).willReturn(restaurants);
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"mia\"")))
                .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    public void detailWithExisted() throws Exception {
        Restaurant restaurant = Restaurant.builder()
                .name("mia")
                .address("Vancouver")
                .id(1004L)
                .build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder().menu("Kimchi").build()));
        Review review = Review.builder().name("Bam").score(5L).description("Good").build();
        restaurant.setReviews(Arrays.asList(review));
        given(restaurantService.getRestaurant(1004L)).willReturn(restaurant);
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"mia\"")))
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"menu\":\"Kimchi\"")))
                .andExpect(content().string(containsString("\"description\":\"Good\"")))
        ;
    }

    @Test
    public void detailWithNotExisted() throws Exception {
        given(restaurantService.getRestaurant(404L)).willThrow(new RestaurantNotFoundException(404L));
        mvc.perform(get("/restaurants/404"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("{}"));
    }
}