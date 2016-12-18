package com.shopmanagment.service;


import com.shopmanagment.entity.Account;

/**
 * Created by Andrei on 11.12.2016.
 */
public interface AccountService {

    public String signup(Account acccount) throws Exception;

    public String login(Account acccount) throws Exception;
}