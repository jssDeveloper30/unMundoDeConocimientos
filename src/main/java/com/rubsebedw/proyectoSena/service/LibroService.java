package com.rubsebedw.proyectoSena.service;

import com.rubsebedw.proyectoSena.model.Libro;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LibroService {
	
	Libro guardar(Libro libro);
	
	Optional<Libro> encontrarPorID(Integer id); 
	
	List<Libro> listar();
	
	Libro encontrarLibro(Libro libro);
	
	void eliminarLibro(Libro libro);
	
	Optional<Libro> encontrarPorIsbn(String isbn);
	
	Page<Libro> listar(Pageable pageable);

}
