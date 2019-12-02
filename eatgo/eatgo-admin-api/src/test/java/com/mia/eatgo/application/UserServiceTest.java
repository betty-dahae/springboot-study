package com.mia.eatgo.application;

import com.mia.eatgo.domain.User;
import com.mia.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    @Mock
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }


    @Test
    public void getUsers(){
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder().email("tester01@test.com")
                .name("tester01")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();
        User user = users.get(0);
        assertThat(user.getName(), is("tester01"));
    }

    @Test
    public void addUser(){
        given(userRepository.save(any())).will(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1004L);
            return user;
        });

        User user = User.builder()
                    .name("tester01")
                    .email("tester01@test.com")
                    .level(1L)
                    .build();

        User created = userService.addUser(user);
        assertThat(created.getId(), is(1004L));
    }

    @Test
    public void updateUser(){
        Long id = 1004L;
        String name = "tester01";
        String email = "tester01@test.com";
        Long level = 2L;
        User mockUser = User.builder()
                        .id(id)
                        .name("tester000")
                        .email(email)
                        .level(level)
                        .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User updated = userService.updateUser(id, name, email, level);

        verify(userRepository).findById(eq(id));

        assertThat(updated.getName(), is("tester01"));
    }

    @Test
    public void deactiveUser(){
        Long id = 1004L;
        User mockUser = User.builder()
                .id(id)
                .name("tester01")
                .email("tester01@test.com")
                .level(2L)
                .build();

        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(1004L);
        verify(userRepository).findById(1004L);
        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(false));
    }

}