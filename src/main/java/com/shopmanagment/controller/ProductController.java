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
@Controller
public class ProductController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Autowired
    @Qualifier("productService")
    private ProductService productService;


    @RequestMapping(value = "/product/{shop_id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShops(@PathVariable("shop_id") long shop_id) throws RestException {
        try {
            List<Product> result = productService.getProducts(shop_id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}/{product_id}", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getShop(@PathVariable("product_id") long id, @PathVariable("shop_id") long shop_id) throws RestException {
        try {
            Product result = productService.getProduct(shop_id, id);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}", method = RequestMethod.POST, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> create(@PathVariable("shop_id") long shop_id, @RequestBody Product product)  throws RestException {
        try {
            if (product.getName() == null || product.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            product.setShop_id(shop_id);
            productService.create(product);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}/{product_id}", method = RequestMethod.PUT, headers = {"Content-type=application/json"} )
    public @ResponseBody
    Map<String, Object> update(@PathVariable("shop_id") long shop_id, @PathVariable("product_id") long id, @RequestBody Product product)  throws RestException {
        try {
            if (product.getName() == null || product.getName() == "") {
                return Ajax.errorResponse("Field name can't be empty.");
            }
            product.setShop_id(shop_id);
            product.setId(id);
            productService.update(product);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/product/{shop_id}/{product_id}", method = RequestMethod.DELETE )
    public @ResponseBody
    Map<String, Object> delete(@PathVariable("shop_id") long shop_id, @PathVariable("product_id") long id)  throws RestException {
        try {
            productService.delete(shop_id, id);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }
}

