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

        /**
     * Metodo que retorna usuarios por su ID.
     * 
     * @return ResponseEntity HTTP 200 OK con el usuario encontrado, o HTTP 404 Not Found si no se encuentra el usuario
     *         en la base de datos.
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long idUsuario) { 
        try { 
            Usuario usuario = this.usuarioService.findByIdUsuario(idUsuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            // Si no encuentra el usuario en la base de datos
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Metodo para crear un nuevo usuario
     * @RequestBody objeto JSON que contiene los datos del usuario a crear, sin el idUsuario.
     * @return ResponseEntity HTTP 201 Created con el usuario creado, o HTTP 400 Bad Request si no se puede crear el usuario
     */
    
     @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) { 
        try { 
            Usuario nuevoUsuario = this.usuarioService.saveUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
        } catch (Exception e) {
            // Si no encuentra el usuario en la base de datos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Método para modificar un usuario ya existente.
     * 
     * @RequestBody objeto JSON que contiene los datos del usuario a actualizar, incluyendo el idUsuario.
     * @return ResponseEntity HTTP 200 OK con el usuario actualizado, o HTTP 400 Bad Request si no se puede actualizar el usuario
     */
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long idUsuario, @RequestBody Usuario usuario) { 
        try { 
            Usuario usuarioExistente = this.usuarioService.findByIdUsuario(idUsuario);
            //usuarioExistente.setIdUsuario(idUsuario); FALTA
            usuarioExistente.setRunUsr(usuario.getRunUsr());
            usuarioExistente.setSnombreUsr(usuario.getSnombreUsr());
            usuarioExistente.setPnombreUsr(usuario.getPnombreUsr());
            usuarioExistente.setApellidosUsr(usuario.getApellidosUsr());
            usuarioExistente.setTelefonoUsr(usuario.getTelefonoUsr());
            usuarioExistente.setCorreoUsr(usuario.getCorreoUsr());
            usuarioExistente.setDireccionUsr(usuario.getDireccionUsr());
            usuarioExistente.setCargo(usuario.getCargo());

            this.usuarioService.saveUsuario(usuarioExistente);
            return ResponseEntity.ok(usuarioExistente);
            
        } catch (Exception e) {
            // Si no encuentra el usuario en la base de datos
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }





}
