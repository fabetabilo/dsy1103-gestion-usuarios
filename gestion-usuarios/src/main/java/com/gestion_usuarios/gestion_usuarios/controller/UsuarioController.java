package com.gestion_usuarios.gestion_usuarios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion_usuarios.gestion_usuarios.model.Cargo;
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
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer idUsuario) { 
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
     * @return ResponseEntity HTTP 200 OK con el usuario actualizado, o HTTP 404 Not Found si no se encuentra el usuario
     *         en la base de datos.
     */
    @PutMapping("/{idUsuario}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Integer idUsuario, @RequestBody Usuario usuario) { 
        try { 
            Usuario usuarioExistente = this.usuarioService.findByIdUsuario(idUsuario);
            usuarioExistente.setIdUsuario(idUsuario);
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
            return ResponseEntity.notFound().build();

        }
    }

    /**
     * Metodo para eliminar un usuario por el ID.
     * @param idUsuario Id del usuario a eliminar.
     * @return HTTP 200 OK si se elimina el usuario, o HTTP 404 Not Found si no se llega a encontrar.
     * 
     */
    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long idUsuario) { 
        try { 
            this.usuarioService.deleteById(idUsuario);
            return ResponseEntity.ok(idUsuario); // OJO
            // return ResponseEntity.noContent().build();
        } catch (Exception e) { 
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Método para contar el número de usuarios existentes.
     * @return HTTP 200 OK con el número de usuarios existentes.
     * 
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countUsuarios() { 
        Long count = usuarioService.count();
        return ResponseEntity.ok(count);
    }
//BUSCAR POR CARGO:
    @GetMapping("/cargo/{codCargo}")
    public ResponseEntity<List<Usuario>> getStoresByCargo(@PathVariable Integer codCargo) {
        Cargo cargo = new Cargo();
        cargo.setCodCargo(codCargo);
        List<Usuario> listaUsuariospc = this.usuarioService.findByCargo(cargo);
        if (listaUsuariospc.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaUsuariospc);
    }
}
