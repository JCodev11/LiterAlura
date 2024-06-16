package com.JCodev.LiterAlura.LiterAlura.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// Clase para convertir los datos obtenidos de la API a un objeto.
public class ConvierteDatos implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    // Método para convertir los datos obtenidos de la API a un objeto.
    @Override
    public <T> T obtenerDatos(String Json, Class<T> clase) {
        try {
            return objectMapper.readValue(Json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
