package com.JCodev.LiterAlura.LiterAlura.Service;

public interface IConvierteDatos {
    <T> T obtenerDatos (String Json, Class<T> clase);
}
