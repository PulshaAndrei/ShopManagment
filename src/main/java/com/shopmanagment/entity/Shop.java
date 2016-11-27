package com.shopmanagment.entity;

import java.util.UUID;

/**
 * Created by Andrei on 27.11.2016.
 */
public class Shop implements DomainObject {

    private long id;
    private String name;
    private String address;
    private String time;

    public Shop(long id, String name, String address, String time) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.time = time;
    }

    public Shop() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
