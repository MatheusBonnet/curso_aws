package com.matheus.RestWithSpringBootUdemy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.matheus.RestWithSpringBootUdemy.config.FileStorageConfig;

@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageConfig.class
})
@EnableAutoConfiguration
@ComponentScan
public class RestWithSpringBootUdemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestWithSpringBootUdemyApplication.class, args);
	}

}
