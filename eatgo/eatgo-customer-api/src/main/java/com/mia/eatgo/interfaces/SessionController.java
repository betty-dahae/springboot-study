package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.UserService;
import com.mia.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SessionController {
    @Autowired
    UserService userService;

    @PostMapping("/session")
    public ResponseEntity<SessionResponseDto> create(@RequestBody SessionRequestDto resource) throws URISyntaxException {


        User user = userService.authenticate(resource.getEmail(), resource.getPassword());
        String accessToken = user.getAccessToken();
        String url ="/session";
        return ResponseEntity.created(new URI(url)).body(SessionResponseDto
                .builder()
                .accessToken(accessToken)
                .build());
    }
}