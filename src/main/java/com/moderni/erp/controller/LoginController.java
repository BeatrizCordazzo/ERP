package com.moderni.erp.controller;

import com.moderni.erp.service.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class LoginController {

    private final UsuarioService usuarioService;

    public LoginController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public boolean iniciarSesion(String correo, String password) {
        return usuarioService.autenticarUsuario(correo, password);
    }
}
