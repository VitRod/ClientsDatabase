package com.vit.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.vit")
@EnableJpaAuditing
@EnableJpaRepositories("com.vit.repository") 
@EntityScan("com.vit.model")

public class ClientsDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientsDatabaseApplication.class, args);
	}

}
