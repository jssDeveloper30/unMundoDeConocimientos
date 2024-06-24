package com.rubsebedw.proyectoSena.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rubsebedw.proyectoSena.model.PrestamoLibro;
import com.rubsebedw.proyectoSena.model.Libro;
import com.rubsebedw.proyectoSena.repository.PrestamoLibroRepositorio;
import com.rubsebedw.proyectoSena.service.LibroService;
import com.rubsebedw.proyectoSena.service.PrestamoLibroService;

@Service
public class PrestamoLibroImpl implements PrestamoLibroService {

    @Autowired
    private PrestamoLibroRepositorio prestamoLibroRepositorio;

    @Autowired
    private LibroService libroService;

    @Override
    public PrestamoLibro guardar(PrestamoLibro prestamo) {
        return prestamoLibroRepositorio.save(prestamo);
    }

    @Override
    public Optional<PrestamoLibro> encontrarPorID(Integer id) {
        return prestamoLibroRepositorio.findById(id);
    }

    @Override
    public Optional<PrestamoLibro> encontrarPorIdentificacionUsuario(String identificacionUsuario) {
        return prestamoLibroRepositorio.findByIdentificacionUsuario(identificacionUsuario);
    }

    @Override
    public List<PrestamoLibro> listar() {
        return (List<PrestamoLibro>) prestamoLibroRepositorio.findAll();
    }

    @Override
    public PrestamoLibro encontrarPrestamo(PrestamoLibro prestamo) {
        return prestamoLibroRepositorio.findById(prestamo.getId()).orElse(null);
    }

    @Override
    public void eliminar(PrestamoLibro prestamo) {
        Optional<Libro> libroOpt = libroService.encontrarPorIsbn(prestamo.getIsbn());
        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            libro.setPrestado(true);
        }
        prestamoLibroRepositorio.delete(prestamo);
    }

    @Override
    public PrestamoLibro procesarPrestamo(PrestamoLibro prestamoLibro) {
        LocalDate fechaDevolucion = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        if (prestamoLibro.getTipoUsuario() == 3) {
            Optional<PrestamoLibro> prestamoExistente = encontrarPorIdentificacionUsuario(prestamoLibro.getIdentificacionUsuario());
            if (prestamoExistente.isPresent()) {
                throw new IllegalArgumentException("El usuario con identificación " + prestamoLibro.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            }
            fechaDevolucion = addDaysSkippingWeekends(fechaDevolucion, 7);
        } else if (prestamoLibro.getTipoUsuario() == 1) {
            fechaDevolucion = addDaysSkippingWeekends(fechaDevolucion, 10);
        } else if (prestamoLibro.getTipoUsuario() == 2) {
            fechaDevolucion = addDaysSkippingWeekends(fechaDevolucion, 8);
        } else if (prestamoLibro.getTipoUsuario() > 3) {
            throw new IllegalArgumentException("Tipo de usuario no permitido en la biblioteca");
        }

        Optional<Libro> libroOpt = libroService.encontrarPorIsbn(prestamoLibro.getIsbn());
        if (libroOpt.isPresent()) {
            Libro libro = libroOpt.get();
            libro.setPrestado(false);
            libroService.guardar(libro);
        }else {
        	
            throw new IllegalArgumentException("Libro no encontrado");
        }

        prestamoLibro.setFechaMaximaDevolucion(fechaDevolucion.format(formatter));
        return guardar(prestamoLibro);
    }

    @Override
    public PrestamoLibro modificarPrestamo(PrestamoLibro prestamoLibro) {
        LocalDate fechaDevolucion = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        if (prestamoLibro.getTipoUsuario() == 3) {
            Optional<PrestamoLibro> prestamoExistente = encontrarPorIdentificacionUsuario(prestamoLibro.getIdentificacionUsuario());
            if (prestamoExistente.isPresent()) {
                throw new IllegalArgumentException("El usuario con identificación " + prestamoLibro.getIdentificacionUsuario() + " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo");
            }
            fechaDevolucion = addDaysSkippingWeekends(fechaDevolucion, 7);
        } else if (prestamoLibro.getTipoUsuario() == 1) {
            fechaDevolucion = addDaysSkippingWeekends(fechaDevolucion, 10);
        } else if (prestamoLibro.getTipoUsuario() == 2) {
            fechaDevolucion = addDaysSkippingWeekends(fechaDevolucion, 8);
        } else if (prestamoLibro.getTipoUsuario() > 3) {
            throw new IllegalArgumentException("Tipo de usuario no permitido en la biblioteca");
        }

        prestamoLibro.setFechaMaximaDevolucion(fechaDevolucion.format(formatter));
        return guardar(prestamoLibro);
    }

    private LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek().name().equals("SATURDAY") || result.getDayOfWeek().name().equals("SUNDAY"))) {
                ++addedDays;
            }
        }
        return result;
    }
}
