package com.heavytiger.entity;

public class User {
    private Integer id;     //主键
    private String username;        //用户名
    private String password_hash;       //密码散列值
    private String salt;        //盐
    private String email;       //邮箱

    public User() {
    }

    public User(Integer id, String username, String password_hash, String salt, String email) {
        this.id = id;
        this.username = username;
        this.password_hash = password_hash;
        this.salt = salt;
        this.email = email;
    }

    public User(String username, String password_hash, String email) {
        this.username = username;
        this.password_hash = password_hash;
        this.email = email;
    }

    public Integer getId() { return id; }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public void setPassword(String password) {
        this.password_hash = password;
    }       // 暂存password

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password_hash='" + password_hash + '\'' +
                ", salt='" + salt + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
