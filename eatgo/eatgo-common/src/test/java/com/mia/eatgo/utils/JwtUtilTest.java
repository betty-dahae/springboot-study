package com.mia.eatgo.utils;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class JwtUtilTest {

    private static final String SECRET = "youcantrevealthesecretkey1234012300040";
    private JwtUtil jwtUtil;

    @Before
    public void setUp(){
        jwtUtil = new JwtUtil(SECRET);
    }
    @Test
    public void createToken() {
        Long id = 1004L;
        String name = "Jhon";
        String token = jwtUtil.createToken(id, name);
        System.out.println(token);
        assertThat(token, containsString("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKaG9uIn0.3Pby0MAJswaKDwuCPr3_L_Ra7FzWOmj_bkKuAJpBG20"));
    }

    @Test
    public void getClaims(){
        Long id = 1004L;
        String name = "Jhon";
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJKaG9uIn0.3Pby0MAJswaKDwuCPr3_L_Ra7FzWOmj_bkKuAJpBG20";

        Claims claims = jwtUtil.getClaims(token);
        assertThat(claims.get("name"), is(name));
    }
}