package com.javajuniordeepseek.application;

import java.util.logging.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EntityScan(basePackages = "com.javajuniordeepseek.model")
@ComponentScan(basePackages = "com.*")
@EnableJpaRepositories(basePackages = "com.javajuniordeepseek.repository")
@EnableTransactionManagement
@Slf4j
public class JavaJuniorDeepSeekApplication {

	public static void main(String[] args) { 
		
		Logger log = Logger.getLogger(JavaJuniorDeepSeekApplication.class.getName());
		
		SpringApplication.run(JavaJuniorDeepSeekApplication.class, args);
		log.info("Conectado Corporação XYZ.");
		System.out.println("Conectado corporação XYZ.");
	}

}
