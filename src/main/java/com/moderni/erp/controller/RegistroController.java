package com.moderni.erp.controller;

import com.moderni.erp.model.Rol;
import com.moderni.erp.service.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class RegistroController {

    private final UsuarioService usuarioService;

    public RegistroController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Rol[] obtenerRoles() {
        return Rol.values();
    }

    public void registrarUsuario(String nombre, String apellido, String password, String telefono, Rol rol, String correo) {
        usuarioService.registrarUsuario(nombre, apellido, password, telefono, rol, correo);
    }
}
