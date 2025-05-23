package com.gestion_usuarios.gestion_usuarios.service;

import com.gestion_usuarios.gestion_usuarios.model.Cargo;
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

    public Usuario findByIdUsuario(long idUsuario) { 
        return usuarioRepository.findById(idUsuario).get();
    }

    public Usuario saveUsuario(Usuario usuario) { 
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long idUsuario) {
        this.usuarioRepository.deleteById(idUsuario);
    }
    
    public List<Usuario> findByCargo(Cargo cargo) {
        return this.usuarioRepository.findByCargo(cargo);
    }

    public Long count() {
        return usuarioRepository.count();
    }


}
