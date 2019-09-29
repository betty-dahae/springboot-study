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
        String account = "Test03";
        String password = "Test03";
        String status = "REGISTERED";
        String email = "Test02@gmail.com";
        String phoneNumber = "010-1234-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        //builder
        User user = User.builder().account(account).password(password)
                .status(status).email(email)
                .phoneNumber(phoneNumber).registeredAt(registeredAt).build();

        //chain
        user.setRegisteredAt(LocalDateTime.now()).setPassword("1234");
/*
        user.setAccount(account);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setStatus(status);
        user.setPassword(password);
        user.setRegisteredAt(registeredAt);
*/

        User createdUser = userRepository.save(user);
        Assert.assertNotNull(createdUser);
        System.out.println("createdUser Id : " + createdUser.getId());


    }

    @Test
    @Transactional
    public void read(){
        String phoneNumber = "010-1234-1234";
        Optional<User> firstUser = userRepository.findById(1L);
        firstUser.ifPresent(user->{
            user.getOrderGroupList().stream().forEach(orderGroup->{
                System.out.println("-------------주문 그룹--------------");
                System.out.println("수령인 " + orderGroup.getRevName());
                System.out.println("수령지 " + orderGroup.getRevAddress());
                System.out.println("총금액 " + orderGroup.getTotalPrice());
                System.out.println("총수량 " + orderGroup.getTotalQuantity());
                System.out.println(orderGroup);

                System.out.println("-------------주문상세--------------");
                orderGroup.getOrderDetailList().stream().forEach(orderDetail -> {
                    System.out.println("파트너스 이름: " + orderDetail.getItem().getPartner().getName());
                    System.out.println("파트너사 카테고리 " + orderDetail.getItem().getPartner().getCategory().getTitle());
                    System.out.println("주문 상품 : " + orderDetail.getItem().getName());
                    System.out.println("주문 상태 : " + orderDetail.getStatus());
                    System.out.println("도착 예정 일자  : " + orderDetail.getArrivalDate());
                });
            });
        });
        Assert.assertNotNull(firstUser);
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
