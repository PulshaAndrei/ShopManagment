package com.shopmanagment.repository;

import com.shopmanagment.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 27.11.2016.
 */

@org.springframework.stereotype.Repository("shopRespitory")

public class ShopRepositoryImpl implements ShopRepository<Shop> {

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public void create(Shop object) {
        jdbcOperations.update("INSERT INTO shop (name, address, time) VALUES (?, ?, ?);", object.getName(), object.getAddress(), object.getTime());
    }

    @Override
    public void update(Shop object) {
       jdbcOperations.update("UPDATE shop SET name = ?, address = ?, time = ? WHERE id = ?;",
                object.getName(), object.getAddress(), object.getTime(), object.getId());
    }

    @Override
    public void delete(long id) {
        jdbcOperations.update("DELETE FROM shop WHERE id = ?;", id);
    }

    @Override
    public List<Shop> getShops() {
        List<Shop> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM shop ORDER BY id;");
        while (rowSet.next()) {
            result.add(new Shop(
                    rowSet.getLong("id"),
                    rowSet.getString("name"),
                    rowSet.getString("address"),
                    rowSet.getString("time")));
        }
        return result;
    }

    @Override
    public Shop getShop(long id) {
        List<Shop> result = new ArrayList<>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM shop WHERE id = ?;", id);
        while (rowSet.next()) {
            result.add(new Shop(
                    rowSet.getLong("id"),
                    rowSet.getString("name"),
                    rowSet.getString("address"),
                    rowSet.getString("time")));
        }
        return result.get(0);
    }
}