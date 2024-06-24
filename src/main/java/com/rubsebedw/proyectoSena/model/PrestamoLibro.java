package com.rubsebedw.proyectoSena.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "prestamolibro")
public class PrestamoLibro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
    private int id;
	

    private String isbn;
    
    @Column(name = "identificacionusuario")
    @Size(max=10, message = "campo alfanumérico de máximo 10 caracteres")
    private String identificacionUsuario;
    
    @Column(name = "tipousuario")
    @Max(value = 9, message = "campo numérico de 1 solo dígito")
    private int tipoUsuario;    
    
    @Column(name = "fechamaximadevolucion")
    private String fechaMaximaDevolucion;    
    
    public PrestamoLibro() {
    } 

	public PrestamoLibro(int id, String isbn, String identificacionUsuario, int tipoUsuario,
			String fechaMaximaDevolucion) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.identificacionUsuario = identificacionUsuario;
		this.tipoUsuario = tipoUsuario;
		this.fechaMaximaDevolucion = fechaMaximaDevolucion;
	}

	public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getIdentificacionUsuario() {
        return identificacionUsuario;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }    
   
    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }   

    public void setId(int id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }    
    
    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

	@Override
	public String toString() {
		return "PrestamoLibro [id=" + id + ", isbn=" + isbn + ", identificacionUsuario=" + identificacionUsuario
				+ ", tipoUsuario=" + tipoUsuario + ", fechaMaximaDevolucion=" + fechaMaximaDevolucion + "]";
	}          

}
