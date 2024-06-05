package com.JCodev.LiterAlura.LiterAlura.Model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public record Datos(
        @JsonAlias("results") List<DatosLibros> resultados) {

}
