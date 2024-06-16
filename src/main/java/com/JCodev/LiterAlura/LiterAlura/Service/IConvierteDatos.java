package com.JCodev.LiterAlura.LiterAlura.Service;

// Interfaz para convertir los datos obtenidos de la API a un objeto.
public interface IConvierteDatos {
    // Método para convertir los datos obtenidos de la API a un objeto.
    <T> T obtenerDatos (String Json, Class<T> clase);
}
