package com.heavytiger.dao.impl;

import com.heavytiger.dao.UserDao;
import com.heavytiger.entity.User;
import org.junit.jupiter.api.Test;

class UserDaoImplTest {

    @Test
    void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsername("admin") == null) {
            System.out.println("用户名可以使用！");
        } else {
            System.out.println("用户名已存在！");
        }
    }

    @Test
    void queryUserByUsernameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        if(userDao.queryUserByUsernameAndPassword("admin","123456") == null) {
            System.out.println("用户名或密码错误，登录失败！");
        } else {
            System.out.println("登录成功！");
        }
    }

    @Test
    void saveUser() {
        UserDao userDao = new UserDaoImpl();
        User user = new User("test02", "123456", "123456@qq.com");
        if(userDao.saveUser(user) == -1){
            System.out.println("注册失败！");
        } else {
            System.out.println("注册成功！");
            user = userDao.queryUserByUsernameAndPassword("test02","123456");
            System.out.println(user.toString());
        }
        userDao.queryUserByUsername("test02");
        user.setEmail("123456@163.com");
        userDao.saveUser(user);
    }
}