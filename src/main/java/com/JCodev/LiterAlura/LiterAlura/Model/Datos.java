package com.JCodev.LiterAlura.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

// Ignora las propiedades que no se encuentran en la clase
@JsonIgnoreProperties(ignoreUnknown = true)

// Clase que recibe los datos de la API
public record Datos(@JsonAlias("results") List<DatosLibros> libros){

}





