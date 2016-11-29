package com.shopmanagment.repository;

import com.shopmanagment.entity.DomainObject;
import com.shopmanagment.entity.Product;

import java.util.List;

/**
 * Created by Andrei on 29.11.2016.
 */
public interface ProductRepository<V extends DomainObject> {

    List<Product> getProducts(long shop_id);

    Product getProduct(long shop_id, long id);

    void create(V object);

    void update(V object);

    void delete(long shop_id, long id);
}