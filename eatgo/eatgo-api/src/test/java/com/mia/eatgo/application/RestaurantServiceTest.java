package com.mia.eatgo.application;

import com.mia.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private ReviewRepository reviewRepository;

    //before : 모든 테스트가 실행 되기 전에 해당 테스트를 반드시 실행
    @Before //스프링 테스트가 아니기때문에 autowired하듯이 IOC 주입이 안되어 다음과 같이 setUp을 해주어야
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);

    }

    private void mockReviewRepository() {

        List<Review> reviews = new ArrayList<>();
        Review review = Review.builder()
                        .name("Bam")
                        .score(5)
                        .description("Good")
                        .build();
        reviews.add(review);

        given(reviewRepository.findAllByRestaurantId(1004L)).willReturn(reviews);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems = new ArrayList<>();
        MenuItem menuItem = MenuItem.builder()
                .menu("Kimchi")
                .build();
        menuItems.add(menuItem);

        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    private void mockRestaurantRepository() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant.builder()
                .name("mia")
                .address("Vancouver")
                .id(1004L)
                .build();
        restaurants.add(restaurant);

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(Optional.of(restaurant));

    }

    @Test
    public void getRestaurantWithExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));
        assertThat(restaurant.getId(), is(1004L));
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertThat(menuItem.getMenu(), is("Kimchi"));
        Review review = restaurant.getReviews().get(0);
        assertThat(review.getDescription(), is("Good"));
    }
    @Test(expected = RestaurantNotFoundException.class)
    public void getRestaurantWithNotExisted(){
        Restaurant restaurant = restaurantService.getRestaurant(404L);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));

    }
    
    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant = invocation.getArgument(0);
            restaurant.setId(1020L);
            return restaurant;
        });

        Restaurant restaurant = Restaurant.builder()
                .name("GalbiZip")
                .address("Toronto")
                .build();

        Restaurant created = restaurantService.addRestaurant(restaurant);
        assertThat(created.getId(), is(1020L));
    }

    @Test
    public void updateRestaurant(){
        Restaurant restaurant = Restaurant.builder()
                .name("Bab Zip")
                .address("Toronto")
                .id(1004L)
                .build();
        given(restaurantRepository.findById(1004L))
                .willReturn(Optional.of(restaurant));
        restaurantService.updateRestaurant(1004L, "Sool Zip", "Vancouver");
        assertThat(restaurant.getName(), is("Sool Zip"));
        assertThat(restaurant.getAddress(), is("Vancouver"));
    }
}