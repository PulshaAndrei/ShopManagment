package com.shopmanagment.repository;

import com.shopmanagment.entity.Account;
import com.shopmanagment.entity.DomainObject;

/**
 * Created by Andrei on 11.12.2016.
 */
public interface AccountRepository<V extends DomainObject> {

    String signup(Account account) throws Exception;

    String login(Account account) throws Exception;
}