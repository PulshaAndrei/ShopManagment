package com.shopmanagment.service;

import com.shopmanagment.entity.Shop;

import java.util.List;

/**
 * Created by Andrei on 27.11.2016.
 */

public interface ShopService {

    public boolean create(long user_id, Shop shop);

    public boolean update(long user_id, Shop shop);

    public boolean delete(long user_id, long id);

    public List<Shop> getShops(long user_id);

    public Shop getShop(long user_id, long id);
}