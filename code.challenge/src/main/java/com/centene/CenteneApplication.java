package com.centene;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@EnableAutoConfiguration
@PropertySources(
		{ 
			@PropertySource("classpath:application.properties")		
		}
)
public class CenteneApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CenteneApplication.class, args);
	}

}
