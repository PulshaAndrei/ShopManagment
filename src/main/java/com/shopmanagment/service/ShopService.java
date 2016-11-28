package com.shopmanagment.service;

import com.shopmanagment.entity.Shop;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 27.11.2016.
 */

public interface ShopService {

    public boolean create(Shop shop);

    public boolean update(Shop shop);

    public boolean delete(long id);

    public List<Shop> getShops();

    public Shop getShop(long id);
}