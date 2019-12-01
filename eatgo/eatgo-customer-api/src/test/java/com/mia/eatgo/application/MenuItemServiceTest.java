package com.mia.eatgo.application;


import com.mia.eatgo.domain.MenuItem;
import com.mia.eatgo.domain.MenuItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MenuItemServiceTest  {

    private MenuItemService menuItemService;
    @Mock
    MenuItemRepository menuItemRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        menuItemService = new MenuItemService(menuItemRepository);
    }

    @Test
    public void bulkUpdate(){

        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(MenuItem.builder().menu("Kimchi").build());   //save
        menuItems.add(MenuItem.builder().id(12L).menu("Kimchi").build()); //update
        menuItems.add(MenuItem.builder().id(123L).destroy(true).build());   //delete
        menuItemService.bulkUpdate(1L, menuItems);


        verify(menuItemRepository, times(2)).save(any());
        verify(menuItemRepository, times(1)).deleteById(eq(123L));
    }
}