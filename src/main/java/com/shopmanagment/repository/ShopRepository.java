package com.shopmanagment.repository;

import com.shopmanagment.entity.DomainObject;
import com.shopmanagment.entity.Shop;

import java.util.List;

/**
 * Created by Andrei on 27.11.2016.
 */

public interface ShopRepository<V extends DomainObject> {

    List<Shop> getShops();

    Shop getShop(long id);

    void create(V object);

    void update(V object);

    void delete(long id);
}