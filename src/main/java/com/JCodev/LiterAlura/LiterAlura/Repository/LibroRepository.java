package com.JCodev.LiterAlura.LiterAlura.Repository;

import com.JCodev.LiterAlura.LiterAlura.Model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libros, Long> {
    List<Libros> findByAutorId(Long autorId);

    Optional<Libros> findByTitulo(String titulo);

    List<Libros> findByIdiomasContaining(String idioma);
}
