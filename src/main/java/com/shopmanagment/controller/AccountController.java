package com.shopmanagment.controller;

import com.shopmanagment.entity.Account;
import com.shopmanagment.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * Created by Andrei on 11.12.2016.
 */
@CrossOrigin(origins = "*")
@Controller
public class AccountController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(ShopController.class);

    @Autowired
    @Qualifier("accountService")
    private AccountService accountService;

    @RequestMapping(value = "/auth/signup", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> signup(@RequestBody Account account) throws RestException {
        try {
            String result = accountService.signup(account);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            return Ajax.errorResponse(e.getMessage());
        }
    }

    @RequestMapping(value = "/auth/login", method = RequestMethod.PUT)
    public @ResponseBody
    Map<String, Object> login(@RequestBody Account account) throws RestException {
        try {
            String result = accountService.login(account);
            return Ajax.successResponse(result);
        } catch (Exception e) {
            return Ajax.errorResponse(e.getMessage());
        }
    }

    public long getUserId(String token) throws RestException {
        String key = "ShopManagmentPassword";
        Claims claims;
        try {
            claims = (Claims) Jwts.parser().setSigningKey(key).parse(token).getBody();
        } catch (Exception ex) {
            throw new AuthenticationServiceException("Token corrupted");
        }
        if (claims.get("token_expiration_date") == null)
            throw new AuthenticationServiceException("Invalid token");
        Date expiredDate = new Date((long) claims.get("token_expiration_date"));
        if (expiredDate.after(new Date()))
            return (Integer) claims.get("userID");
        else
            throw new AuthenticationServiceException("Token expired date error");
    }
}
