package com.shopmanagment.repository;

import com.shopmanagment.entity.DomainObject;
import com.shopmanagment.entity.Shop;

import java.util.List;

/**
 * Created by Andrei on 27.11.2016.
 */

public interface ShopRepository<V extends DomainObject> {

    List<Shop> getShops(long user_id);

    Shop getShop(long user_id, long id);

    void create(long user_id, V object);

    void update(long user_id, V object);

    void delete(long user_id, long id);
}