package com.shopmanagment.service;

import com.shopmanagment.entity.Product;

import java.util.List;

/**
 * Created by Andrei on 29.11.2016.
 */
public interface ProductService {

    public boolean create(long user_id, Product product);

    public boolean update(long user_id, Product product);

    public boolean delete(long user_id, long shop_id, long id);

    public List<Product> getProducts(long user_id, long shop_id);

    public Product getProduct(long user_id, long shop_id, long id);
}
