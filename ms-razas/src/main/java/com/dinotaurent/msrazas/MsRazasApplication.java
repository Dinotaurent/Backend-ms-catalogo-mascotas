package com.dinotaurent.msrazas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EntityScan({"com.dinotaurent.mscommonsmascotasrazas.models.entity"})
public class MsRazasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsRazasApplication.class, args);
	}

}
