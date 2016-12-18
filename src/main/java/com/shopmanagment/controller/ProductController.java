package com.shopmanagment.controller;

import com.shopmanagment.entity.Product;
import com.shopmanagment.service.ProductService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Andrei on 29.11.2016.
 */

@CrossOrigin(origins = "*")
@Controller
public class ProductController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Autowired
    @Qualifier("productService")
    private ProductService productService;

    @Autowired
    private AccountController accountController;

    @RequestMapping(value = "/product/{shop_id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShops(@RequestHeader(value="Authorization") String token,
                                 @PathVariable("shop_id") long shop_id) throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            List<Product> result = productService.getProducts(user_id, shop_id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}/{product_id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShop(@RequestHeader(value="Authorization") String token,
                                @PathVariable("product_id") long id,
                                @PathVariable("shop_id") long shop_id) throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            Product result = productService.getProduct(user_id, shop_id, id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}", method = RequestMethod.POST, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> create(@RequestHeader(value="Authorization") String token,
                               @PathVariable("shop_id") long shop_id,
                               @RequestBody Product product)  throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            if (product.getName() == null || product.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            product.setShop_id(shop_id);
            productService.create(user_id, product);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}/{product_id}", method = RequestMethod.PUT, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> update(@RequestHeader(value="Authorization") String token,
                               @PathVariable("shop_id") long shop_id,
                               @PathVariable("product_id") long id,
                               @RequestBody Product product)  throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            if (product.getName() == null || product.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            product.setShop_id(shop_id);
            product.setId(id);
            productService.update(user_id, product);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}/{product_id}", method = RequestMethod.DELETE )
    public @ResponseBody
    Map<String, Object> delete(@RequestHeader(value="Authorization") String token,
                               @PathVariable("shop_id") long shop_id,
                               @PathVariable("product_id") long id)  throws RestException {
        long user_id = accountController.getUserId(token);
        try {
            productService.delete(user_id, shop_id, id);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }
}

