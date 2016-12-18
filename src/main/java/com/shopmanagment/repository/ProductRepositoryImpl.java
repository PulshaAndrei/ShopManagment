package com.shopmanagment.repository;

import com.shopmanagment.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 29.11.2016.
 */
@org.springframework.stereotype.Repository("productRespitory")

public class ProductRepositoryImpl implements ProductRepository<Product> {

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public void create(long user_id, Product object) {
        jdbcOperations.update("INSERT INTO product (user_id, shop_id, name, description) VALUES (?, ?, ?, ?);",
                user_id, object.getShop_id(), object.getName(), object.getDescription());
    }

    @Override
    public void update(long user_id, Product object) {
        jdbcOperations.update("UPDATE product SET name = ?, description = ? WHERE user_id = ? AND shop_id = ? AND id = ?;",
                object.getName(), object.getDescription(), user_id, object.getShop_id(), object.getId());
    }

    @Override
    public void delete(long user_id, long shop_id, long id) {
        jdbcOperations.update("DELETE FROM product WHERE user_id = ? AND shop_id = ? AND id = ?;", user_id, shop_id, id);
    }

    @Override
    public List<Product> getProducts(long user_id, long shop_id) {
        List<Product> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM product WHERE user_id = ? AND shop_id = ? ORDER BY id;", user_id, shop_id);
        while (rowSet.next()) {
            result.add(new Product(
                    rowSet.getLong("id"),
                    rowSet.getLong("shop_id"),
                    rowSet.getString("name"),
                    rowSet.getString("description")));
        }
        return result;
    }

    @Override
    public Product getProduct(long user_id, long shop_id, long id) {
        List<Product> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM product WHERE user_id = ? AND shop_id = ? AND id = ?;", user_id, shop_id, id);
        while (rowSet.next()) {
            result.add(new Product(
                    rowSet.getLong("id"),
                    rowSet.getLong("shop_id"),
                    rowSet.getString("name"),
                    rowSet.getString("description")));
        }
        return result.get(0);
    }
}
