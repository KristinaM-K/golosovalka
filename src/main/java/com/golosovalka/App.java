package com.golosovalka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@SpringBootApplication - указывает, что это главный класс приложения Spring Boot, который должен быть запущен
//@EntityScan - указывает на классы, которые должны быть сканированы при использовании JPA
@SpringBootApplication
@EntityScan(basePackageClasses = {
		App.class,
		Jsr310JpaConverters.class
})
public class App {
	//@PostConstruct - указывает на метод, который должен быть выполнен после того, как бины приложения будут инициализированы
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	//метод main, который запускает приложение Spring Boot
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
