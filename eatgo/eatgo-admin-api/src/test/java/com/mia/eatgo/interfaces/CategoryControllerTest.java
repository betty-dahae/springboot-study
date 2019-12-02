package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.CategoryService;
import com.mia.eatgo.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void list() throws Exception {

        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("Chicken").build());

        given(categoryService.getCategories()).willReturn(categories);

        mvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Chicken")));

    }

    @Test
    public void create() throws Exception {
        Category mockCategory = Category.builder().name("Chicken").build();
        given(categoryService.addCategory("Chicken")).willReturn(mockCategory);

        mvc.perform(post("/categories")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(" {\"name\":\"Chicken\"}"))
                    .andExpect(status().isCreated())
                    .andExpect(content().string("{}"));

        verify(categoryService).addCategory("Chicken");
    }
}