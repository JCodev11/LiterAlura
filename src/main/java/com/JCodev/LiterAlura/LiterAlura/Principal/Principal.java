package com.JCodev.LiterAlura.LiterAlura.Principal;

import com.JCodev.LiterAlura.LiterAlura.Model.*;
import com.JCodev.LiterAlura.LiterAlura.Repository.AutorRepository;
import com.JCodev.LiterAlura.LiterAlura.Repository.LibroRepository;
import com.JCodev.LiterAlura.LiterAlura.Service.AutorService;
import com.JCodev.LiterAlura.LiterAlura.Service.ConsumoAPI;
import com.JCodev.LiterAlura.LiterAlura.Service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
// Clase principal de la aplicación.
public class Principal {
    // URL base de la API.
    private static final String URL_BASE = "https://gutendex.com/books/";
    private final ConsumoAPI consumoAPI = new ConsumoAPI();
    private final ConvierteDatos conversor = new ConvierteDatos();

    // Inyección de dependencias.
    @Autowired
    private AutorRepository repositorio;

    @Autowired
    private LibroRepository repository;

// Inyección de dependencia de la clase AutorService.
    private final AutorService autorService;

    @Autowired
    public Principal(AutorService autorService) {
        this.autorService = autorService;
    }

    // Creamos un método para ejecutar la aplicacion
    public void ejecutar() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos = conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        Scanner scanner = new Scanner(System.in);
        System.out.println(".....................................................................................................");
        System.out.println("Bienvenido al Catalogo de Libros:\nLiterAlura.com\n");
        System.out.println(".....................................................................................................");
        System.out.println("");

        boolean appEnEjecucion = true;
        while (appEnEjecucion) {
            System.out.println("Seleccione Opción Deseada:");
            System.out.println("");
            System.out.println("+------------------------------------------------+");
            System.out.println("|1. Buscar libro por título                      |");
            System.out.println("+------------------------------------------------+");
            System.out.println("|2. Lista de  libros registrados                 |");
            System.out.println("+------------------------------------------------+");
            System.out.println("|3. Lista de autores registrados                 |");
            System.out.println("+------------------------------------------------+");
            System.out.println("|4. Lista de autores vivos en un determinado año |");
            System.out.println("+------------------------------------------------+");
            System.out.println("|5. Lista de  libros por idioma                  |");
            System.out.println("+------------------------------------------------+");
            System.out.println("|6. Salir                                        |");
            System.out.println("+------------------------------------------------+");
            System.out.println("");
            System.out.print("Ingrese el número correspondiente: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            String consulta;

            switch (opcion) {
                case 1:
                System.out.print("Ingrese el título del libro a buscar: ");
                String tituloLibro = scanner.nextLine();
                json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
                var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

                Optional<DatosLibros> libroBuscado = datosBusqueda.libros().stream()
                        .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                        .findFirst();

                if (libroBuscado.isPresent()) {
                    DatosLibros libro = libroBuscado.get();

                    // Verificar si el libro ya existe en la base de datos
                    Optional<Libros> libroExistente = repository.findByTitulo(libro.titulo());

                    if (libroExistente.isPresent()) {
                        System.out.println("El libro ya existe en la base de datos. Por favor, ingrese otro libro.");
                    } else {
                        System.out.println("Libro encontrado:");
                        System.out.println("");
                        System.out.println("Título: " + libro.titulo());
                        System.out.println("Autor(es):");

                        // Guardar autores
                        List<Autores> autoresGuardados = new ArrayList<>();
                        libro.autor().forEach(autor -> {
                            System.out.println("  Nombre: " + autor.nombre());
                            System.out.println("  Fecha de Nacimiento: " + autor.fechaDeNacimiento());
                            System.out.println("  Fecha de Defunción: " + autor.fechaDeDefuncion());

                            // Crear y guardar el autor
                            Autores autorEntidad = new Autores(autor);
                            autoresGuardados.add(repositorio.save(autorEntidad));
                        });

                        // Guardar el libro y establecer la relación con el autor
                        for (Autores autor : autoresGuardados) {
                            Libros libroEntidad = new Libros();
                            libroEntidad.setTitulo(libro.titulo());
                            libroEntidad.setAutor(autoresGuardados.get(0)); // Establece el autor para el libro
                            libroEntidad.setIdiomas(libro.idiomas());
                            libroEntidad.setNumeroDescargas(libro.numeroDescargas());
                            repository.save(libroEntidad);
                        }

                        System.out.println("Idiomas: " + libro.idiomas());
                        System.out.println("Número de descargas: " + libro.numeroDescargas());
                    }
                } else {
                    System.out.println("Libro no encontrado");
                }
                break;

                case 2:
                    consulta = "Consultar libros registrados";
                    System.out.println("Lista de libros registrados:");
                    //llamar al método para consultar la lista de libros registrados
                    consultarLibrosRegistrados();
                    break;

                case 3:
                    consulta = "Consultar Lista de Autores";
                    // llamar al método para consultar la lista de autores
                    consultarListaAutores();
                    break;

                case 4:
                    consulta = "Autores vivos";
                    System.out.println("Mostrando lista de autores vivos en un determinado año...");
                    // Pedir al usuario el año
                    System.out.print("Ingrese el año para verificar autores vivos: ");
                    int anoConsulta = scanner.nextInt();
                    scanner.nextLine(); // Consume el newline

                    // Obtener la lista de autores vivos en el año especificado
                    List<Autores> autoresVivosEnAno = autorService.obtenerAutoresVivosEnAno(anoConsulta);

                    if (autoresVivosEnAno.isEmpty()) {
                        System.out.println("No hay autores vivos en el año " + anoConsulta);
                    } else {
                        System.out.println("Lista de autores vivos en el año " + anoConsulta + ":");
                        autoresVivosEnAno.forEach(autor -> {
                            System.out.println("Nombre: " + autor.getNombre());
                            System.out.println("Fecha de Nacimiento: " + autor.getFechaDeNacimiento());
                            System.out.println("Fecha de Defunción: " + autor.getFechaDeDefuncion());
                            System.out.println("-----------------------------------------");
                        });
                    }
                break;

                case 5:
                    consulta = "Idioma";
                    System.out.println("Mostrando lista de libros por idioma...");
                    System.out.println("ingrese el idioma: es (español) o en (inglés)...");
                    String idioma = scanner.nextLine();
                    consultarLibrosPorIdioma(idioma);
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    appEnEjecucion = false;
                    continue;
                default:
                    System.out.println("Opción inválida");
                    break;
            }

            System.out.println(".....................................................................................................................");
            System.out.println("");

 // Esperar unos segundos y preguntar si desea continuar
            try {
                Thread.sleep(1000); // Espera 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.print("¿Desea continuar? (s/n): ");
            String continuar = scanner.nextLine();
            if (!continuar.equalsIgnoreCase("s")) {
                appEnEjecucion = false;
            }
        }

        scanner.close();
    }
// crear un método para consultar la lista de autores
    private void consultarListaAutores() {
        System.out.println("Lista de autores registrados:");
        List<Autores> autoresRegistrados = autorService.obtenerTodosLosAutores();

        autoresRegistrados.forEach(autor -> {
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Fecha de Nacimiento: " + autor.getFechaDeNacimiento());
            System.out.println("Fecha de Defunción: " + autor.getFechaDeDefuncion());
            System.out.println("-----------------------------------------------------------------");
        });
    }

// crear un método para consultar la lista de libros registrados
        private void consultarLibrosRegistrados() {
            System.out.println("Lista de libros registrados:");
            List<Libros> librosRegistrados = autorService.obtenerTodosLosLibros();

            librosRegistrados.forEach(libro -> {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor().getNombre());
                System.out.println("Idiomas: " + libro.getIdiomas());
                System.out.println("Número de descargas: " + libro.getNumeroDescargas());
                System.out.println("-----------------------------------------------------------------");
            });
        }

//crear un método para consultar la lista de libros por idioma
    private void consultarLibrosPorIdioma(String idioma) {
        System.out.println("Lista de libros registrados en idioma " + idioma + ":");
        List<Libros> librosPorIdioma = autorService.obtenerLibrosPorIdioma(idioma);

        librosPorIdioma.forEach(libro -> {
            System.out.println("Título: " + libro.getTitulo());
            System.out.println("Autor: " + libro.getAutor().getNombre());
            System.out.println("Idiomas: " + libro.getIdiomas());
            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
            System.out.println("-----------------------------------------------------------------");
        });
    }


}
