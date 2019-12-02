package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.UserService;
import com.mia.eatgo.domain.User;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;

    @Test
    public void list() throws Exception {

        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().email("tester01@test.com").name("tester01").level(1L).build());
        given(userService.getUsers()).willReturn(mockUsers);

        mvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("tester01")));
    }

    @Test
    public void create() throws Exception {

        given(userService.addUser(any())).will(invocation -> {
            User restaurant = invocation.getArgument(0);
            return User.builder().email("tester01@test.com").name("tester01").level(1L).build();
        });

        mvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester01@test.com\",\"name\":\"tester01\",\"level\":1}"))
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"));

        verify(userService).addUser(any());
    }



    @Test
    public void update() throws Exception {

        mvc.perform(patch("/users/1004")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"email\":\"tester01@test.com\",\"name\":\"tester01\",\"level\":2}"))
                    .andExpect(status().isOk());

        Long id = 1004L;
        String name = "tester01";
        String email = "tester01@test.com";
        Long level = 2L;

        verify(userService).updateUser(eq(id), eq(name), eq(email), eq(level));


    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/users/1005"))
                .andExpect(status().isOk());

        verify(userService).deactiveUser(1005L);
    }
}