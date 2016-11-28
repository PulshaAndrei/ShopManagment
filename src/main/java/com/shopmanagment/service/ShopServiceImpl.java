package com.shopmanagment.service;

import com.shopmanagment.entity.Shop;
import com.shopmanagment.repository.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    public boolean create(Shop shop) {
        try {
            shopRepository.create(shop);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean update(Shop shop) {
        try {
            shopRepository.update(shop);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        try {
            shopRepository.delete(id);
            return true;
        } catch (Exception e) {
            LOG.error("ERROR SAVING DATA: " + e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Shop> getShops() {
        return shopRepository.getShops();
    }

    @Override
    public Shop getShop(long id) {
        return shopRepository.getShop(id);
    }
}