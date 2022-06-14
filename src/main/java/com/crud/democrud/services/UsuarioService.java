package com.crud.democrud.services;

import com.crud.democrud.models.UsuarioModel;
import com.crud.democrud.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return (ArrayList<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }


    public ArrayList<UsuarioModel>  obtenerPorPrioridad(Integer prioridad) {
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public ArrayList<UsuarioModel>  obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public boolean eliminarUsuario(Long id) {
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch(Exception err){
            return false;
        }
    }

    public boolean eliminarPorEmail(String email) {
        Logger logger = Logger.getLogger("logger");
        try{
            ArrayList<UsuarioModel> users = usuarioRepository.findByEmail(email);
            UsuarioModel user = users.get(0);
            usuarioRepository.deleteById(user.getId());
            return true;
        }catch(Exception err){
            logger.warning(err.getMessage());
            return false;
        }
    }


    
}