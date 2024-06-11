package com.JCodev.LiterAlura.LiterAlura.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Column (unique = true)
    private String nombre;
    private String fechaDeNacimiento;
    private String fechaDeDefuncion;

    public Autores() {
    }

    public Autores(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        this.fechaDeDefuncion = datosAutor.fechaDeDefuncion();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(String fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }

    @Override
    public String toString() {
        return "Autores{" +
                "nombre='" + nombre + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                ", fechaDeDefuncion='" + fechaDeDefuncion + '\'' +
                '}';
    }
}
