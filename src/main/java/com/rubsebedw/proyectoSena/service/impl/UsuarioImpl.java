package com.rubsebedw.proyectoSena.service.impl;

import com.rubsebedw.proyectoSena.model.Usuario;
import com.rubsebedw.proyectoSena.repository.UsuarioRepository;
import com.rubsebedw.proyectoSena.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> encontrarPorID(Integer idUsuario) {
        Optional<Usuario> encontrar = usuarioRepository.findById(idUsuario);
        return encontrar;
    }

    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    @Override
    public Optional<Usuario> encontarPorIdUsuario(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario);
    }


    @Override
    public Page<Usuario> listar(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }
}
