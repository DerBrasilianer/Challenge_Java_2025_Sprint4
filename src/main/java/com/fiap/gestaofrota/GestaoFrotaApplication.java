package com.fiap.gestaofrota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GestaoFrotaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoFrotaApplication.class, args);
	}

}
