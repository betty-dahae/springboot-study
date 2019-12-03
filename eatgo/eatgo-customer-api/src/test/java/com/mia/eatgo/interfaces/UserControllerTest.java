package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.UserService;
import com.mia.eatgo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    UserService userService;


    @Test
    public void create() throws Exception{

        User mockUser = User.builder()
                .email("tester@example.com")
                .name("tester")
                .password("test")
                .build();
        given(userService.registerUser("tester@example.com", "tester", "test"))
                .willReturn(mockUser);

        mvc.perform(post("/users")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"email\":\"tester@example.com\", \"name\":\"tester\",\"password\":\"test\"}"))
                    .andExpect(status().isCreated());

        verify(userService).registerUser(eq("tester@example.com"), eq("tester"), eq("test"));

    }



}