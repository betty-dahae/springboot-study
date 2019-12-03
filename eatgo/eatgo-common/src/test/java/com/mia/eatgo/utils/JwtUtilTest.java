package com.mia.eatgo.utils;

import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class JwtUtilTest {

    @Test
    public void createToken() {
        String secret = "youcantrevealthesecretkey1234012300040";
        JwtUtil jwtUtil = new JwtUtil(secret);
        Long id = 1004L;
        String name = "";
        String token = jwtUtil.createToken(id, name);
        assertThat(token, containsString("."));
    }
}