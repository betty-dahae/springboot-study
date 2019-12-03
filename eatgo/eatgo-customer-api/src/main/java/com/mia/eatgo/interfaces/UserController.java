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
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {
        String url = "/users/"+resource.getId();
        userService.registerUser(resource.getEmail(), resource.getName(), resource.getPassword());
        return ResponseEntity.created(new URI(url)).body("{}");
    }

}
