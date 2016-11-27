package com.shopmanagment.repository;

import com.shopmanagment.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Types;
import java.util.*;

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
        Object[] params = new Object[] { object.getId(), object.getName(), object.getAddress(), object.getTime() };
        int[] types = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

        jdbcOperations.update("UPDATE shop SET (cast(? as UUID), ?)\n" +
                "   WHERE id = '" + object.getId() + "';", params, types);
    }

    @Override
    public void delete(Shop object) {
        jdbcOperations.update("DELETE FROM shop\n" +
                " WHERE id = '" + object.getId() + "';");
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
        SqlRowSet rowSet = jdbcOperations.queryForRowSet("SELECT * FROM shop WHERE id == ?;", id);
        return new Shop(
                rowSet.getLong("id"),
                rowSet.getString("name"),
                rowSet.getString("address"),
                rowSet.getString("time"));
    }
}