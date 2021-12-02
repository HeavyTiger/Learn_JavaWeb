package com.heavytiger.dao;

import com.heavytiger.entity.User;

public interface UserDao {


    /**
     * 使用用户名去查询该用户是否存在
     * @param username 需要查询的用户名
     * @return 若存在返回User 若不存在返回null
     */
    public User queryUserByUsername(String username);

    /**
     * 使用用户名和密码去查询该用户是否存在
     * @param   username 需要查询的用户名
     * @param   password 用户输入的密码
     * @return  若存在返回User 若不存在返回null
     */
    public User queryUserByUsernameAndPassword(String username, String password);

    /**
     * @param user 需要保存到数据库中的User对象
     * @return  若返回 1表示保存成功，若不为1表示保存失败
     */
    public int saveUser(User user);
}
