package com.JCodev.LiterAlura.LiterAlura.Service;

import com.JCodev.LiterAlura.LiterAlura.Model.Autores;
import com.JCodev.LiterAlura.LiterAlura.Model.Libros;
import com.JCodev.LiterAlura.LiterAlura.Repository.AutorRepository;
import com.JCodev.LiterAlura.LiterAlura.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

//  Crear la clase AutorService con los métodos necesarios para obtener autores y libros
@Service
public class AutorService {
// Inyectar los repositorios de autores y libros
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
   private LibroRepository libroRepository;

    public List<Autores> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }

  public List<Libros> obtenerTodosLosLibros() {
       return libroRepository.findAll();
   }
public List<Autores> obtenerAutoresVivosEnAno(int ano) {
    List<Autores> todosLosAutores = autorRepository.findAll();

    return todosLosAutores.stream()
            .filter(autor -> esAutorVivoEnAno(autor, ano))
            .collect(Collectors.toList());
}

    private boolean esAutorVivoEnAno(Autores autor, int ano) {
        // Parsear solo el año de la fecha de nacimiento y defunción
        int yearNacimiento = parsearAno(autor.getFechaDeNacimiento());
        Integer yearDefuncion = autor.getFechaDeDefuncion() != null ?
                parsearAno(autor.getFechaDeDefuncion()) : null;

        // Verificar si el autor está vivo en el año especificado
        return (yearDefuncion == null || yearDefuncion > ano) &&
                yearNacimiento <= ano;
    }

    private int parsearAno(String fecha) {
        // Parsear el año de la fecha en formato 'yyyy'
        return Integer.parseInt(fecha.substring(0, 4));
    }
    public List<Libros> obtenerLibrosPorIdioma(String idioma) {
        // Realizar la consulta en el repositorio de libros según el idioma
        return libroRepository.findByIdiomasContaining(idioma);
    }
}
