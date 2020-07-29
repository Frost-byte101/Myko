package com.watcher.domain;


import com.watcher.utils.IJson;


public class User implements IJson {


    private Integer id;
    private String username;
    private String password;
    private String identity;
    private String status;

    public User(Integer id, String username, String password, String identity, String status) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.identity = identity;
        this.status = status;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity='" + identity + '\'' +
                ", status='" + status + '\'' +
                '}';
    }


    @Override
    public String toJson() {
        return trans(toString());
    }
}
