package com.example.admin.repository;


import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import com.example.admin.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


public class UserRepositoryTest extends AdminApplicationTests {


    @Autowired //(DI = Dependency Injection)
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("abc3@gmail.com");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");
        user.setEmail("abc3@gmail.com");
        user.setPhoneNumber("010-1234-1234");

        User createdUser = userRepository.save(user);
        System.out.println("createdUser Id : " + createdUser.getId());
    }

    @Test
    @Transactional
    public void read(){
        //Optional<User> selectedUser = userRepository.findById(6L);
        /*Optional<User> selectedUser = userRepository.findByAccount("abc@gmail.com");

        selectedUser.ifPresent(user -> {
            System.out.println("user: " + user);
            System.out.println("email: " + user.getEmail());
            user.getOrderDetailList().stream().forEach(detail ->{
                System.out.println("detail : " + detail.getId());
                //Item item = detail.getItem();
               // System.out.println(item.getName());
            });
        });*/
    }

    @Test
    public void update(){

        Optional<User> selectedUser = userRepository.findById(4L);

        selectedUser.ifPresent(user -> {
            user.setAccount("haha@gmail.com");
            user.setUpdatedAt(LocalDateTime.now());
            user.setUpdatedBy("admin");
            userRepository.save(user);
        });

    }

    @Test
    @Transactional //it would be rollback after test is done
    public void delete(){
        Optional<User> selectedUser = userRepository.findById(5L);

        Assert.assertTrue(selectedUser.isPresent());

        selectedUser.ifPresent(user -> {
            userRepository.delete(user);
        });

        Optional<User> deletedUser = userRepository.findById(5L);

        Assert.assertFalse(deletedUser.isPresent());
    }

}
