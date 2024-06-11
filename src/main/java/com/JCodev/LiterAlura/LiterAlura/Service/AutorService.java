package com.JCodev.LiterAlura.LiterAlura.Service;

import com.JCodev.LiterAlura.LiterAlura.Model.Autores;
import com.JCodev.LiterAlura.LiterAlura.Repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autores> obtenerTodosLosAutores() {
        return autorRepository.findAll();
    }
}
