package com.dinotaurent.msmascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
@EntityScan({"com.dinotaurent.mscommonsmascotasrazas.models.entity"})
public class MsMascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsMascotasApplication.class, args);
	}

}
