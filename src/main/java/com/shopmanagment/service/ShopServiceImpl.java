package com.shopmanagment.service;

import com.shopmanagment.entity.Shop;
import com.shopmanagment.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andrei on 27.11.2016.
 */
@Service("shopService")
public class ShopServiceImpl implements ShopService {

    private static final Logger LOG = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    @Qualifier("shopRespitory")
    private ShopRepository shopRepository;

    @Override
    public boolean create(long user_id, Shop shop) {
        try {
            shopRepository.create(user_id, shop);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(long user_id, Shop shop) {
        try {
            shopRepository.update(user_id, shop);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(long user_id, long id) {
        try {
            shopRepository.delete(user_id, id);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Shop> getShops(long user_id) {
        return shopRepository.getShops(user_id);
    }

    @Override
    public Shop getShop(long user_id, long id) {
        return shopRepository.getShop(user_id, id);
    }
}