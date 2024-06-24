package com.rubsebedw.proyectoSena.service.impl;

import com.rubsebedw.proyectoSena.model.Libro;
import com.rubsebedw.proyectoSena.repository.LibroRepository;
import com.rubsebedw.proyectoSena.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Service
public class LibroImpl implements LibroService {
	
	@Autowired
	private LibroRepository libroRepositorio;

	@Override
	public Libro guardar(Libro libro) {
		return libroRepositorio.save(libro);
	}

	@Override
	public Optional<Libro> encontrarPorID(Integer id) {
		Optional<Libro> encontrar = libroRepositorio.findById(id);
		return encontrar;
	}

	@Override
	public List<Libro> listar() {
		return (List<Libro>) libroRepositorio.findAll();
	}

	@Override
	public Libro encontrarLibro(Libro libro) {
		return libroRepositorio.findById(libro.getId()).orElse(null);
	}

	@Override
	public void eliminarLibro(Libro libro) {
		libroRepositorio.delete(libro);
		
	}
	
	@Override
    public Optional<Libro> encontrarPorIsbn(String isbn) {
        return libroRepositorio.findByIsbn(isbn);
    }
	
    @Override
    public Page<Libro> listar(Pageable pageable) {
        return libroRepositorio.findAll(pageable);
    }

}
