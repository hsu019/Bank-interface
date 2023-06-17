package com.example.demo.service;

import com.example.demo.models.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void loadUserByUsername() {
        UserDetails user = userService.loadUserByUsername("user");
        System.out.println(user.getAuthorities());
    }

    @Test
    void selectUserFromToken() {
        User user = userService.selectUserFromToken("12345");
        System.out.println(user);
    }

    @Test
    void selectCardFromUserId() {
        List<String> strings = userService.selectCardFromUserId(1);
        System.out.println(strings);
    }
}