package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.ReviewService;
import com.mia.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ReviewController.class)
public class ReviewControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    //eq: When we use argument matchers, then all the arguments should use matchers.
    // If we want to use a specific value for an argument, then we can use eq() method.
    @Test
    public void createWithValidAttributes() throws Exception {
        given(reviewService.addReview(eq(1L), any())).willReturn(Review.builder()
                                                                .id(123L)
                                                                .name("Bam")
                                                                .score(3)
                                                                .description("So So")
                                                                .build());
        mvc.perform(post("/restaurants/1/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Bam\",\"score\":3,\"description\":\"So So\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1/reviews/123"));


        verify(reviewService).addReview(eq(1L), any());
    }
    @Test
    public void createWithInValidAttributes() throws Exception {
        mvc.perform(post("/restaurants/404/reviews")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{}"))
                .andExpect(status().isBadRequest());


        verify(reviewService,never()).addReview(eq(1004L), any());
    }
}