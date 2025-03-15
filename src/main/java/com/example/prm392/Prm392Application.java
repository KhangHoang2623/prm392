package com.example.prm392;

import com.example.prm392.config.SecurityProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({SecurityProperties.class})
public class Prm392Application {

	public static void main(String[] args) {
		SpringApplication.run(Prm392Application.class, args);
	}

}
