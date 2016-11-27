package com.shopmanagment;

import com.shopmanagment.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShopmanagmentApplication {

	public static void main(String[] args) {

		SpringApplication.run(new Class<?>[] {ShopmanagmentApplication.class, JpaConfig.class}, args);
		SpringApplication.run(ShopmanagmentApplication.class, args);
	}
}
