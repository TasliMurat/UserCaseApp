package com.proje.konutapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class KonutappApplication {

	public static void main(String[] args) {
		SpringApplication.run(KonutappApplication.class, args);
	}

}
