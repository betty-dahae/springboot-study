package com.mia.eatgo.interfaces;

import com.mia.eatgo.domain.MenuItemRepository;
import com.mia.eatgo.domain.MenuItemRepositoryImpl;
import com.mia.eatgo.domain.RestaurantRepository;
import com.mia.eatgo.domain.RestaurantRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //스프링을 이용하여 테스트 실행
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @SpyBean(RestaurantRepositoryImpl.class) //WebMvcTest를 사용할시 제대로된 repository를 사용 할 수 없기 때문에 SpyBean주석을 통해 repository의 의존성을 주입시켜줘야함
    private RestaurantRepository restaurantRepository;
    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;
    @Test
    public void list() throws Exception {
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"mia\"")))
                .andExpect(content().string(containsString("\"id\":1004")));
    }

    @Test
    public void detail() throws Exception {
        mvc.perform(get("/restaurants/1004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"mia\"")))
                .andExpect(content().string(containsString("\"id\":1004")))
                .andExpect(content().string(containsString("\"menu\":\"kimchi\"")));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"bam\"")))
                .andExpect(content().string(containsString("\"id\":2020")));
    }
}