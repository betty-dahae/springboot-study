package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class ItemRepositoryTest extends AdminApplicationTests {

    @Autowired
    ItemRepository itemRepository;


    @Test
    public void create(){
        Item item = new Item();
        item.setName("Note book");
        item.setPrice(100000);
        item.setContent("Macbook Pro 15inch");

        itemRepository.save(item);
        Assert.assertNotNull(item);
    }

    @Test
    public void read(){
        Long id = 1L;

        Optional<Item> item = itemRepository.findById(id);

        Assert.assertNotNull(item);
        item.ifPresent(i -> {
            System.out.println(i);
        });
    }

}
