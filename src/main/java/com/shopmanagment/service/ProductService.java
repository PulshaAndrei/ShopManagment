package com.shopmanagment.service;

import com.shopmanagment.entity.Product;

import java.util.List;

/**
 * Created by Andrei on 29.11.2016.
 */
public interface ProductService {

    public boolean create(Product product);

    public boolean update(Product product);

    public boolean delete(long shop_id, long id);

    public List<Product> getProducts(long shop_id);

    public Product getProduct(long shop_id, long id);
}
