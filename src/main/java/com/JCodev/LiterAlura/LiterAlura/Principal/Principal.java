package com.JCodev.LiterAlura.LiterAlura.Principal;

import com.JCodev.LiterAlura.LiterAlura.Service.ConsumoAPI;
import com.JCodev.LiterAlura.LiterAlura.Service.ConvierteDatos;

public class Principal {
    private static final String URL_BASE = "https://gutendex.com/books/";
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos Conversor = new ConvierteDatos();

    public void ejecutar() {
        var json = consumoAPI.obtenerDatos(URL_BASE);
        System.out.println(json);
    }

}
