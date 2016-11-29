package com.shopmanagment.entity;

/**
 * Created by Andrei on 29.11.2016.
 */
public class Product implements DomainObject {

    private long id;
    private long shop_id;
    private String name;
    private String description;

    public Product(long id, long shop_id, String name, String description) {
        this.id = id;
        this.shop_id = shop_id;
        this.name = name;
        this.description = description;
    }

    public Product() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getShop_id() {
        return shop_id;
    }

    public void setShop_id(long shop_id) {
        this.shop_id = shop_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
