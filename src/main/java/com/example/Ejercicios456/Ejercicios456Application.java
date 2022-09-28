package com.example.Ejercicios456;

import com.example.Ejercicios456.entitys.Laptop;
import com.example.Ejercicios456.repositorys.LaptopRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class Ejercicios456Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Ejercicios456Application.class, args);

		LaptopRepository repository = (LaptopRepository) context.getBean(LaptopRepository.class);


		repository.save(new Laptop(null, 750.00,"Acer"));
		repository.save(new Laptop(null, 500.50,"Mac"));
	}

}
