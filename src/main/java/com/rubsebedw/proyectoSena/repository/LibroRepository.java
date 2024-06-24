package com.rubsebedw.proyectoSena.repository;

import com.rubsebedw.proyectoSena.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByIsbn(String isbn);
}
