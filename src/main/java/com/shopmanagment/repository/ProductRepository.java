package com.shopmanagment.repository;

import com.shopmanagment.entity.DomainObject;
import com.shopmanagment.entity.Product;

import java.util.List;

/**
 * Created by Andrei on 29.11.2016.
 */
public interface ProductRepository<V extends DomainObject> {

    List<Product> getProducts(long user_id, long shop_id);

    Product getProduct(long user_id, long shop_id, long id);

    void create(long user_id, V object);

    void update(long user_id, V object);

    void delete(long user_id, long shop_id, long id);
}