package com.JCodev.LiterAlura.LiterAlura.Principal;

import com.JCodev.LiterAlura.LiterAlura.Model.Datos;
import com.JCodev.LiterAlura.LiterAlura.Model.DatosAutor;
import com.JCodev.LiterAlura.LiterAlura.Model.DatosLibros;
import com.JCodev.LiterAlura.LiterAlura.Service.ConsumoAPI;
import com.JCodev.LiterAlura.LiterAlura.Service.ConvierteDatos;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();

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

            // Menú para seleccionar
            System.out.println("Seleccione Opción Deseada:");
            System.out.println("");
            System.out.println("+-----------------------------+");
            System.out.println("|1. Buscar libro por título                      |");
            System.out.println("+-----------------------------+");
            System.out.println("|2. Lista de  libros registrados                 |");
            System.out.println("+-----------------------------+");
            System.out.println("|3. Lista de autores registrados                 |");
            System.out.println("+-----------------------------+");
            System.out.println("|4. Lista de autores vivos en un determinado año |");
            System.out.println("+-----------------------------+");
            System.out.println("|5. Lista de  libros por idioma                  |");
            System.out.println("+-----------------------------+");
            System.out.println("|6. Salir                                        |");
            System.out.println("+-----------------------------+");
            System.out.println("");
            System.out.print("Ingrese el número correspondiente: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character left by nextInt()

            String consulta;

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String tituloLibro = scanner.nextLine();
                    json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
                    var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
                    Optional<DatosLibros> libroBuscado = datosBusqueda.libros().stream()
                            .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                            .findFirst();
                    if (libroBuscado.isPresent()) {
                        DatosLibros libro = libroBuscado.get();
                        System.out.println("Libro encontrado:");
                        System.out.println("Título: " + libro.titulo());
                        System.out.println("Autor(es):");
                        libro.autor().forEach(autor -> {
                            System.out.println("  Nombre: " + autor.nombre());
                            System.out.println("  Fecha de Nacimiento: " + autor.fechaDeNacimiento());
                        });
                        System.out.println("Idiomas: " + libro.idiomas());
                        System.out.println("Número de descargas: " + libro.numeroDescargas());
                    } else {
                        System.out.println("Libro no encontrado");
                    }
                    break;

                case 2:
                    consulta = "Consultar libros registrados";
                    System.out.println("Lista de libros registrados:");
                    datos.libros().forEach(libro -> {
                        System.out.println("Título: " + libro.titulo());
                        System.out.println("Autor(es):");
                        libro.autor().forEach(autor -> System.out.println("  - " + autor.nombre()));
                        System.out.println("Idiomas: " + libro.idiomas());
                        System.out.println("Número de descargas: " + libro.numeroDescargas());
                        System.out.println("-----------------------------------------------------------------");
                    });
                    break;
                case 3:
                    consulta = "Autores";
                    // Implementa la lógica para mostrar la lista de autores registrados
                    System.out.println("Mostrando lista de autores registrados...");
                    break;
                case 4:
                    consulta = "Autores vivos";
                    // Implementa la lógica para mostrar la lista de autores vivos en un determinado año
                    System.out.println("Mostrando lista de autores vivos en un determinado año...");
                    break;
                case 5:
                    consulta = "Idioma";
                    // Implementa la lógica para mostrar la lista de libros por idioma
                    System.out.println("Mostrando lista de libros por idioma...");
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
                Thread.sleep(3000); // Espera 3 segundos
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

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.ejecutar();
    }
}
