package com.rubsebedw.proyectoSena.service;

import java.util.List;
import java.util.Optional;

import com.rubsebedw.proyectoSena.model.PrestamoLibro;

public interface PrestamoLibroService {
	
	PrestamoLibro guardar(PrestamoLibro prestamo);
	
	Optional<PrestamoLibro> encontrarPorID(Integer id); 
	
	Optional<PrestamoLibro> encontrarPorIdentificacionUsuario(String identificacionUsuario);
	
	public List<PrestamoLibro> listar();
	
	public PrestamoLibro encontrarPrestamo(PrestamoLibro prestamo);
	
	public void eliminar(PrestamoLibro prestamo);
	
    PrestamoLibro procesarPrestamo(PrestamoLibro prestamoLibro);
    
    PrestamoLibro modificarPrestamo(PrestamoLibro prestamoLibro);

}
