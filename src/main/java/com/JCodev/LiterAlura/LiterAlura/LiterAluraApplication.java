package com.JCodev.LiterAlura.LiterAlura;

import com.JCodev.LiterAlura.LiterAlura.Principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	// Inyección de dependencia de la clase Principal.
	@Autowired
	private Principal principal;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		principal.ejecutar();
	}
}
