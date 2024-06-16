package com.JCodev.LiterAlura.LiterAlura.Model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Libros")
public class Libros {
    // Para establecer la primary key de la tabla en la base de datos.
    @Id
    // Para que se genere el ID automáticamente y sea  autoincrementable en la db.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

//Entidad que se relaciona con la tabla de autores (relación de Muchos a Uno).
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autores autor;
    private String idiomas;
    private Double numeroDescargas;

    // Constructor vacío
    public Libros() {
    }
//constructor con todos los atributos de la clase Libros.
    public Libros(Long id, String titulo, Autores autor, String idiomas, Double numeroDescargas) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.idiomas = idiomas;
        this.numeroDescargas = numeroDescargas;
    }

//Getters and Setters para los atributos de la clase Libros.
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autores getAutor() {
        return autor;
    }

    public void setAutor(Autores autor) {
        this.autor =autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas.toString();
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

//Método toString para mostrar los atributos de la clase Libros.
    @Override
    public String toString() {
        return "Libros{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor + // Asegúrate de que autor no sea nulo
                ", idiomas='" + idiomas + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                '}';
    }
}
