package com.mia.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void creation(){
        User user = User.builder()
                .email("tester01@test.com")
                .name("tester1")
                .level(100L)
                .build();

        assertThat(user.getName(), is("tester1"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void accessToken() {
        User user = User.builder().password("ACCESSTOKEN").build();
        assertThat(user.getAccessToken(), is("ACCESSTOKE"));
    }


    @Test
    public void accessTokenWithNoPassword() {
        User user = new User();
        assertThat(user.getAccessToken(), is(""));
    }

}