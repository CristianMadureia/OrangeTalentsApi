package br.cristian.zupteste.desafioorangetalents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = {"br.cristian.zupteste.desafioorangetalents.address"})
public class DesafioOrangetalentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioOrangetalentsApplication.class, args);
	}
}
