package com.shopmanagment.controller;

import com.shopmanagment.entity.Shop;
import com.shopmanagment.service.ShopService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Andrei on 27.11.2016.
 */

@Controller
public class ShopController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Autowired
    @Qualifier("shopService")
    private ShopService shopService;

    @RequestMapping(value = "/shop", method = RequestMethod.POST, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> create(@RequestBody Shop shop)  throws RestException {
        Logger.getLogger(ExceptionHandlerController.class).debug(shop.toString());
        try {
            LOG.debug(shop.toString());
            if (shop == null) {
                return Ajax.successResponse(shop);
            }
            shopService.create(shop);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShops() throws RestException {
        try {
            List<Shop> result = shopService.getShops();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

}
