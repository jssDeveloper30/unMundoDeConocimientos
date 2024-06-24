package com.rubsebedw.proyectoSena.service;

import com.rubsebedw.proyectoSena.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario guardar(Usuario usuario);
    Optional<Usuario> encontrarPorID(Integer idUsuario);
    List<Usuario> listar();
    Usuario encontrarUsuario(Usuario usuario);
    public void eliminarUsuario(Usuario usuario);
    Optional<Usuario> encontarPorIdUsuario(Integer idUsuario);
    Page<Usuario> listar(Pageable pageable);
}
