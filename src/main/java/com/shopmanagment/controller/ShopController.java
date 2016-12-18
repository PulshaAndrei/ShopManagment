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

    @Autowired
    private AccountController accountController;

    @RequestMapping(value = "/shop", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShops(@RequestHeader(value="Authorization") String token) throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            List<Shop> result = shopService.getShops(user_id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop/{shop_id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShop(@RequestHeader(value="Authorization") String token,
                                @PathVariable("shop_id") long id) throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            Shop result = shopService.getShop(user_id, id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop", method = RequestMethod.POST, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> create(@RequestHeader(value="Authorization") String token,
                               @RequestBody Shop shop)  throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            if (shop.getName() == null || shop.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            shopService.create(user_id, shop);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop/{shop_id}", method = RequestMethod.PUT, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> update(@RequestHeader(value="Authorization") String token,
                               @PathVariable("shop_id") long id,
                               @RequestBody Shop shop)  throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            if (shop.getName() == null || shop.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            shop.setId(id);
            shopService.update(user_id, shop);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/shop/{shop_id}", method = RequestMethod.DELETE )
    public @ResponseBody
    Map<String, Object> delete(@RequestHeader(value="Authorization") String token,
                               @PathVariable("shop_id") long id)  throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            shopService.delete(user_id, id);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

}
