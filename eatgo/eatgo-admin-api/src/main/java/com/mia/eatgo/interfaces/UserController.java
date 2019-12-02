package com.mia.eatgo.interfaces;

import com.mia.eatgo.application.UserService;
import com.mia.eatgo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

@RestController
public class UserController {

    // 1. User List
    // 2. User create
    // 3. User update
    // 4. User delete -> level: 0
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> list(){
        return userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<?> create(@RequestBody User resource) throws URISyntaxException {

        User user = userService.addUser(resource);
        String url = "/users/"+user.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }

    @PatchMapping("/users/{id}")
    public String update(@PathVariable("id") Long id, @RequestBody User resource){

        userService.updateUser(id, resource.getName(), resource.getEmail(), resource.getLevel());
        return "{}";
    }

    @DeleteMapping("users/{id}")
    public String delete(@PathVariable("id") Long id){

        userService.deactiveUser(id);
        return "{}";
    }

}
