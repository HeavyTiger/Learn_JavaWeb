package com.heavytiger.service.impl;

import com.heavytiger.entity.User;
import com.heavytiger.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {

    UserService userService = new UserServiceImpl();

    @Test
    void signUpUser() {
        for(int i = 0; i < 20; i++) {
            String username = "test" + i;
            String password = "12345678";
            String email = "12345678@qq.com";
            userService.signUpUser(new User(username, password, email));
        }
    }

    @Test
    void signInUser() {
        for(int i = 0; i < 20; i++) {
            String username = "test" + i;
            String password = "12345678";
            User user = new User(username, password,null);
            user = userService.signInUser(user);
            System.out.println(user.toString());
        }
    }

    @Test
    void existsUsername() {
        for(int i = 0; i < 30; i++) {
            String username = "test" + i;
            System.out.println(username + " is exist? " + userService.existsUsername(username));
        }
    }
}