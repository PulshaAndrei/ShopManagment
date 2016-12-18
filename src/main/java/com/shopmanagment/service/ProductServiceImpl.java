package com.shopmanagment.service;

import com.shopmanagment.entity.Product;
import com.shopmanagment.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andrei on 29.11.2016.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    @Qualifier("productRespitory")
    private ProductRepository productRepository;

    @Override
    public boolean create(long user_id, Product product) {
        try {
            productRepository.create(user_id, product);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(long user_id, Product product) {
        try {
            productRepository.update(user_id, product);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(long user_id, long shop_id, long id) {
        try {
            productRepository.delete(user_id, shop_id, id);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Product> getProducts(long user_id, long shop_id) {
        return productRepository.getProducts(user_id, shop_id);
    }

    @Override
    public Product getProduct(long user_id, long shop_id, long id) {
        return productRepository.getProduct(user_id, shop_id, id);
    }
}