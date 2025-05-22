package com.gestion_usuarios.gestion_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_usuarios.gestion_usuarios.model.Usuario;
import com.gestion_usuarios.gestion_usuarios.service.UsuarioService;

@RestController
@RequestMapping("api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /**
     * Metodo que retorna una lista de todos los usuarios existentes
     * 
     * @return ResponseEntity Lista sin contenido (HTTP 204 No Content), o ResponseEntity de lista de tiendas existentes (HTTP 200 OK)
     */
    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> listaUsuarios = this.usuarioService.findAll();
        if (listaUsuarios.isEmpty()) {
            // Si no encuentra usuarios en la base de datos
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuarios);

    }






}
