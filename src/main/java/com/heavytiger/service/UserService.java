package com.heavytiger.service;

import com.heavytiger.entity.User;

public interface UserService {

    /**
     * 使用输入的各种信息进行注册
     * @param user 注册的账户
     */
    public void signUpUser(User user);

    /**
     * 使用输入的信息进行登录
     * @param user 需要登录的账户
     */
    public User signInUser(User user);

    /**
     * 查找是否存在某个用户
     * @param username 查找用户是否存在
     * @return 若存在返回true,否则返回false
     */
    public boolean existsUsername(String username);

}
