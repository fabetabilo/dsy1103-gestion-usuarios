package com.gestion_usuarios.gestion_usuarios.service;

import com.gestion_usuarios.gestion_usuarios.model.Usuario;
import com.gestion_usuarios.gestion_usuarios.repository.UsuarioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional

public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository; // Llama los métodos.



    public List<Usuario> findAll() { 
        return usuarioRepository.findAll();
    }

    public Usuario findByIdUsuario(Integer idUsuario) { 
        return usuarioRepository.findById(idUsuario).get();
    }

    public Usuario save(Usuario usuario) { 
        return usuarioRepository.save(usuario);
    }

    
}
