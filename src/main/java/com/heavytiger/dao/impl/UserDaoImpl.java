package com.heavytiger.dao.impl;

import com.heavytiger.dao.BaseDao;
import com.heavytiger.dao.UserDao;
import com.heavytiger.entity.User;
import com.heavytiger.utils.HexKit;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * 使用用户的帐号在数据库中进行索引
     *
     * @param username 需要查询的用户名
     * @return 若查询到，说明用户名已经存在，该系统不允许出现重名
     */
    @Override
    public User queryUserByUsername(String username) {
        //System.out.println(username);
        String sql = "SELECT * FROM t_user WHERE username = ?";
        return queryForOne(User.class, sql, username);
    }

    /**
     * 使用账号和密码在数据库中进行搜索，即登录操作
     *
     * @param username 需要查询的用户名
     * @param password 用户输入的密码
     * @return 返回查询到的User对象，若未查询到，返回null
     */
    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        //获取对应账户的盐值
        String sql4salt = "SELECT salt FROM t_user WHERE username = ?";
        String salt = queryForSingleValue(String.class, sql4salt, username);
//        System.out.println(salt);
        if (salt == null) return null;
        else {
            try {
                HexKit hexKit = HexKit.getInstance();
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//                System.out.println(password + salt);
//                System.out.println(Arrays.toString((password + salt).getBytes(StandardCharsets.UTF_8)));
                byte[] tmpPasswordBytes = messageDigest.digest((password + salt).getBytes(StandardCharsets.UTF_8));
                String tmpPassword = hexKit.byte2hex(tmpPasswordBytes);
//                System.out.println(tmpPassword);
                String sql4user = "SELECT * FROM t_user WHERE username = ? and password_hash = ?";
                return queryForOne(User.class, sql4user, username, tmpPassword);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 将User进行储存，若是第一次存储，此时password_hash存储明文密码，将自动构造盐值进行加密。
     * 禁止在该方法中对用户名和密码进行更新
     *
     * @param user 需要保存到数据库中的User对象
     * @return 若成功则返回1，否则失败
     */
    @Override
    public int saveUser(User user) {
        if (user.getSalt() == null) {        //若此时盐值为null，说明是第一次插入数据，将构造盐值，此时password存储密码原文，传入后进行加密
            if (!createSalt(user)) return -1;
            String sql_insert = "INSERT INTO t_user(username, password_hash, salt, email) values(?, ?, ?, ?)";
            return update(sql_insert, user.getUsername(), user.getPassword_hash(), user.getSalt(), user.getEmail());
        } else {
            String sql_update = "UPDATE t_user SET email = ? WHERE username = ?";
            return update(sql_update, user.getEmail(), user.getUsername());
        }
    }

    private boolean createSalt(User user) {
        byte[] byteSalt = new byte[16];
        try {
            SecureRandom random = new SecureRandom();
            HexKit hexKit = HexKit.getInstance();
            random.nextBytes(byteSalt);
            String stringSalt = hexKit.byte2hex(byteSalt);
//            System.out.println(stringSalt);
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            String pwh = hexKit.byte2hex(messageDigest.digest((user.getPassword_hash() + stringSalt).getBytes(StandardCharsets.UTF_8)));
//            System.out.println("password_hash: " + pwh);
            user.setSalt(stringSalt);
            user.setPassword_hash(pwh);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
