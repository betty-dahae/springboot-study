
package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.EmailNotExistedException;
import com.mia.eatgo.application.PasswordWrongException;
import com.mia.eatgo.application.UserService;
import com.mia.eatgo.domain.EmailExistedException;
import com.mia.eatgo.domain.User;
import com.mia.eatgo.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {


    @Autowired
    MockMvc mvc;

    @MockBean
    JwtUtil jwtUtil;

    @MockBean
    UserService userService;


    @Test
    public void create() throws Exception{

        Long id = 1004L;
        String email = "tester@example.com";
        String name = "John";
        String password = "test";
        User mockUser = User.builder().id(id).name(name).email(email).password(password).build();
        given(userService.authenticate(email, password)).willReturn(mockUser);
        given(jwtUtil.createToken(id, name)).willReturn(".");
        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("accessToken")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq(email), eq(password));
    }
    @Test
    public void createWithWrongPassword() throws Exception{

        given(userService.authenticate("tester@example.com", "x")).willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"tester@example.com\", \"name\":\"tester\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("tester@example.com"), eq("x"));
    }
    @Test
    public void createWithNotExistedEmail() throws Exception{

        given(userService.authenticate("x", "test")).willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x\", \"name\":\"tester\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x"), eq("test"));
    }

}
