package com.rubsebedw.proyectoSena.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity(name = "libro")
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "isbn")
    private String isbn;

    @Column(name = "autor")
    private String autor;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha_publicacion")
    private String fechaPublicacion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "editorial")
    private String editorial;

    @Column(name = "prestado")
    private boolean prestado;

    public Libro() {
    }

    // Getters y Setters
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public boolean isPrestado() {
		return prestado;
	}

	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
    @Override
    public String toString() {
        return "Libro [id=" + id + ", isbn=" + isbn + ", autor=" + autor + ", titulo=" + titulo + 
                ", fechaPublicacion=" + fechaPublicacion + ", categoria=" + categoria + 
                ", editorial=" + editorial + ", prestado=" + prestado + "]";
    }
}

