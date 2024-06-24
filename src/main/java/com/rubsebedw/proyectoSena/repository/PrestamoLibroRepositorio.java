package com.rubsebedw.proyectoSena.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.rubsebedw.proyectoSena.model.PrestamoLibro;

public interface PrestamoLibroRepositorio extends CrudRepository<PrestamoLibro, Integer>{

	Optional<PrestamoLibro> findByIdentificacionUsuario(String identificacionUsuario);

}
