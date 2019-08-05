package com.ibaseit.JpaRepoTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
public class JpaRepoTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpaRepoTestApplication.class, args);
	}

}
