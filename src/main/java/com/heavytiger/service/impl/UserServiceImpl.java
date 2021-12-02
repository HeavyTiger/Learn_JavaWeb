package com.heavytiger.service.impl;

import com.heavytiger.dao.UserDao;
import com.heavytiger.dao.impl.UserDaoImpl;
import com.heavytiger.entity.User;
import com.heavytiger.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();        // 用来操作数据库

    /**
     * 使用输入的各种信息进行注册
     * @param user 注册的账户
     */
    @Override
    public void signUpUser(User user) {
        userDao.saveUser(user);
    }

    /**
     * 使用输入的信息进行登录
     * @param user 需要登录的账户
     */
    @Override
    public User signInUser(User user) {
        return userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword_hash());
    }

    /**
     * 查找是否存在某个用户
     *
     * @param username 查找用户是否存在
     * @return 若存在返回true,否则返回false
     */
    @Override
    public boolean existsUsername(String username) {
        // 若没查询到，表示可以使用，此处返回值为false，否则不能使用，返回false
        return userDao.queryUserByUsername(username) != null;
    }
}
