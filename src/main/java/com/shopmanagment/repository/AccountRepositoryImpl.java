package com.shopmanagment.repository;

import com.shopmanagment.entity.Account;
import io.jsonwebtoken.JwtBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 11.12.2016.
 */

@org.springframework.stereotype.Repository("accountRespitory")

public class AccountRepositoryImpl implements AccountRepository<Account> {

    @Autowired
    protected JdbcOperations jdbcOperations;

    @Override
    public String signup(Account account) throws Exception {
        if (account.getUsername() == "" || account.getPassword() == "") {
            throw new Exception("Username or password can't be empty");
        }

        if (getAccountByUsername(account.getUsername()) != null) {
            throw new Exception("This username already exist.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(account.getPassword());
        jdbcOperations.update("INSERT INTO account (username, password) VALUES (?, ?);",
                account.getUsername(), hashedPassword);
        return getToken(account);
    }

    @Override
    public String login(Account account) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Account serverAccount = getAccountByUsername(account.getUsername());
        if (serverAccount == null || !encoder.matches(account.getPassword(), serverAccount.getPassword())) {
            throw new Exception("Incorrect username or password.");
        }
        return getToken(serverAccount);
    }

    public Account getAccountByUsername(String username) {
        try {
            Account account = (Account) jdbcOperations.queryForObject(
                    "SELECT * FROM account WHERE username = ?", new Object[]{username},
                    new BeanPropertyRowMapper(Account.class));
            return account;
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public String getToken(Account account) {
        Map<String, Object> tokenData = new HashMap<>();
        tokenData.put("userID", account.getId());
        tokenData.put("username", account.getUsername());
        tokenData.put("token_create_date", new Date().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        tokenData.put("token_expiration_date", calendar.getTime());
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setExpiration(calendar.getTime());
        jwtBuilder.setClaims(tokenData);
        String key = "ShopManagmentPassword";
        String token = jwtBuilder.signWith(SignatureAlgorithm.HS512, key).compact();
        return token;
    }
}