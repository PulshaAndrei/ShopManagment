package com.shopmanagment.service;

import com.shopmanagment.entity.Account;
import com.shopmanagment.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Andrei on 11.12.2016.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(ShopServiceImpl.class);

    @Autowired
    @Qualifier("accountRespitory")
    private AccountRepository accountRepository;

    @Override
    public String signup(Account account) throws Exception {
        try {
            return accountRepository.signup(account);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public String login(Account account) throws Exception {
        try {
            return accountRepository.login(account);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
