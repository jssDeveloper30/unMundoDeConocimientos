package com.rubsebedw.proyectoSena.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rubsebedw.proyectoSena.model.Libro;
import com.rubsebedw.proyectoSena.model.PrestamoLibro;
import com.rubsebedw.proyectoSena.service.LibroService;
import com.rubsebedw.proyectoSena.service.PrestamoLibroService;

@Controller
public class PrestamoControlador {

    @Autowired
    private PrestamoLibroService prestamoLibroService;

    @Autowired
    private LibroService libroService;

    @GetMapping("/login")
    public String login() {
        return "login"; // Devuelve el nombre de la plantilla HTML de la página de login personalizada
    }

    @GetMapping("/")
    public String mostrarTodo(Model model) {
        var prestamos = prestamoLibroService.listar();
        model.addAttribute("prestamos", prestamos);
        return "index";
    }

    @GetMapping("/index")
    public String sebasPage() {
        return "index";
    }

    @GetMapping("/listadoprestamos")
    public String librosPage(Model model) {
        var prestamos = prestamoLibroService.listar();
        model.addAttribute("prestamos", prestamos);
        return "prestamos";
    }

    @GetMapping("/prueba")
    public String pruebaPage() {
        return "prueba"; 
    }

    @GetMapping("/rotacion")
    public String rotacionPage() {
        return "rotacion"; 
    }

    @GetMapping("/agregar")
    public String agregar(Model model) {
        List<Libro> librosPrestados = libroService.listar().stream()
                                        .filter(Libro::isPrestado)
                                        .collect(Collectors.toList());
        model.addAttribute("libros", librosPrestados);
        model.addAttribute("prestamoLibro", new PrestamoLibro());
        return "agregar";
    }


    @GetMapping("/agregarlibro")
    public String agregarLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "agregarlibro";
    }

    @GetMapping("/listadolibrosdos")
    public String mostrarLibros(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Libro> libroPage = libroService.listar(pageable);
        model.addAttribute("libros", libroPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", libroPage.getTotalPages());
        return "listadolibrosdos";
    }

    @PostMapping("/guardar")
    public String guardarPrestamo(@ModelAttribute("prestamoLibro") PrestamoLibro prestamoLibro, Model model) {
        try {
            prestamoLibroService.procesarPrestamo(prestamoLibro);
            return "redirect:/listadoprestamos";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "mensaje";
        }
    }

    @PostMapping("/guardarlibro")
    public String guardarLibro(@ModelAttribute("libro") Libro libro) {
        libro.setPrestado(true);
        libroService.guardar(libro);
        return "redirect:/listadolibrosdos";
    }

    @PostMapping("/modificar")
    public String modificarPrestamo(@ModelAttribute("prestamolibro") PrestamoLibro prestamoLibro, Model model) {
        try {
            prestamoLibroService.modificarPrestamo(prestamoLibro);
            return "redirect:/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "mensaje";
        }
    }

    @GetMapping("/editar/{id}")
    public String encontrarId(@PathVariable int id, Model model) {
        Optional<PrestamoLibro> prestamoOptional = prestamoLibroService.encontrarPorID(id);

        if (prestamoOptional.isPresent()) {
            PrestamoLibro prestamolibro = prestamoOptional.get();
	        model.addAttribute("id", prestamolibro.getId());
	        model.addAttribute("isbn", prestamolibro.getIsbn());
	        model.addAttribute("identificacionUsuario", prestamolibro.getIdentificacionUsuario());
	        model.addAttribute("tipoUsuario", prestamolibro.getTipoUsuario());
	        model.addAttribute("fechaMaximaDevolucion", prestamolibro.getFechaMaximaDevolucion());
//            model.addAttribute("prestamolibro", prestamolibro);
            
            return "modificar";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/modificarlibro")
    public String modificarLibro(@ModelAttribute("libro") Libro libro) {
        libroService.guardar(libro);
        return "redirect:/listadolibrosdos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable int id) {
        Optional<PrestamoLibro> prestamo = prestamoLibroService.encontrarPorID(id);
        prestamo.ifPresent(prestamoLibroService::eliminar);
        return "redirect:/listadoprestamos";
    }

    @GetMapping("/eliminarlibro/{id}")
    public String eliminarLibro(@PathVariable int id) {
        Optional<Libro> libro = libroService.encontrarPorID(id);
        libro.ifPresent(libroService::eliminarLibro);
        return "redirect:/listadolibrosdos";
    }

    @GetMapping("/editarlibro/{id}")
    public String encontrarIdLibro(@PathVariable int id, Model model) {
        Optional<Libro> libro = libroService.encontrarPorID(id);

        if (libro.isPresent()) {
//            model.addAttribute("libro", libro.get());
			model.addAttribute("id", libro.get().getId());
			model.addAttribute("isbn", libro.get().getIsbn());
			model.addAttribute("autor", libro.get().getAutor());
			model.addAttribute("titulo", libro.get().getTitulo());
			model.addAttribute("fechaPublicacion", libro.get().getFechaPublicacion());
			model.addAttribute("categoria", libro.get().getCategoria());
			model.addAttribute("editorial", libro.get().getEditorial());
			model.addAttribute("prestado", libro.get().isPrestado());
            return "modificarlibro";
        } else {
            return "redirect:/listadolibrosdos";
        }
    }
}
