//package com.JCodev.LiterAlura.LiterAlura;
//
//import com.JCodev.LiterAlura.LiterAlura.Model.Autores;
//import com.JCodev.LiterAlura.LiterAlura.Principal.Principal;
//
//import com.JCodev.LiterAlura.LiterAlura.Repository.AutorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.util.List;
//
//@SpringBootApplication
//public class LiterAluraApplication implements CommandLineRunner{
//@Autowired
//private AutorRepository autorRepository;
//
//
//	public static void main(String[] args) {
//		SpringApplication.run(LiterAluraApplication.class, args);
//	}
//
//	@Override
//	public void run(String... args) throws Exception {
//		Principal principal = new Principal(autorRepository);
//		principal.ejecutar();
//
//
//	}
//
//
//}
//
//
//

package com.JCodev.LiterAlura.LiterAlura;

import com.JCodev.LiterAlura.LiterAlura.Principal.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

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
