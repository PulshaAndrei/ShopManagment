package com.shopmanagment.entity;

/**
 * Created by Andrei on 11.12.2016.
 */
public class Account implements DomainObject {

    private long id;
    private String username;
    private String password;

    public Account(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Account() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

}
