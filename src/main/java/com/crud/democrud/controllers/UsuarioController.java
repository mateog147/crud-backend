package com.crud.democrud.controllers;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario) {
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id) {
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping("/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad) {
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean ok = this.usuarioService.eliminarUsuario(id);
        if (ok) {
            return "Se eliminó el usuario con id " + id;
        } else {
            return "No pudo eliminar el usuario con id" + id;
        }
    }


    @GetMapping("/email")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("email") String email) {
       return this.usuarioService.obtenerPorEmail(email);
    }

    @DeleteMapping(path = "/email")
    public String eliminarPorEmail(@RequestParam("email") String email) {
        Logger logger = Logger.getLogger("logger");
        boolean ok = this.usuarioService.eliminarPorEmail(email);
        logger.info(email);
        if (ok) {

            logger.info("Elimnado");
            return "Se eliminó el usuario con email " + email;
        } else {
            logger.info("No eliminado");
            return "No pudo eliminar el usuario con email" + email;
        }
    }
}