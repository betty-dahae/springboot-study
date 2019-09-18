package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.OrderDetail;
import com.example.admin.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class OrderDetailRepositoryTest extends AdminApplicationTests {

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void create(){
        OrderDetail orderDetail = new OrderDetail();

        //orderDetail.setItemId(3L);
        orderDetail.setOrderAt(LocalDateTime.now());

        //orderDetail.setUser(user);

        orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(orderDetail);
    }

    @Test
    public void read(){
    }

    @Test
    public void update(){


    }

    @Test
    @Transactional //it would be rollback after test is done
    public void delete(){

    }

}
