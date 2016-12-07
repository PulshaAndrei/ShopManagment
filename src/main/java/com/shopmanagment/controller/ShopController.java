package com.shopmanagment.controller;

import com.shopmanagment.entity.Shop;
import com.shopmanagment.service.ShopService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Andrei on 27.11.2016.
 */

@CrossOrigin(origins = "*")
@Controller
public class ShopController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Autowired
    @Qualifier("shopService")
    private ShopService shopService;

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

    @RequestMapping(value = "/shop/{shop_id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShop(@PathVariable("shop_id") long id) throws RestException {
        try {
            Shop result = shopService.getShop(id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop", method = RequestMethod.POST, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> create(@RequestBody Shop shop)  throws RestException {
        try {
            if (shop.getName() == null || shop.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            shopService.create(shop);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop/{shop_id}", method = RequestMethod.PUT, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> update(@PathVariable("shop_id") long id, @RequestBody Shop shop)  throws RestException {
        try {
            if (shop.getName() == null || shop.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            shop.setId(id);
            shopService.update(shop);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop/{shop_id}", method = RequestMethod.DELETE )
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("shop_id") long id)  throws RestException {
        try {
            shopService.delete(id);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }
}
