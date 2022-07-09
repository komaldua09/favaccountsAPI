package com.learning.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FavouriteAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FavouriteAccountsApplication.class, args);
	}

}
