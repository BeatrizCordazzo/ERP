package com.moderni.erp.service;

import com.moderni.erp.model.Rol;
import com.moderni.erp.model.Usuario;
import com.moderni.erp.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario registrarUsuario(String nombre, String apellido, String password, String telefono, Rol rol, String correo) {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setTelefono(telefono);
        usuario.setCorreo(correo);
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    @Transactional(readOnly = true)
    public boolean autenticarUsuario(String correo, String password) {
        Optional<Usuario> usuario = usuarioRepository.findFirstByCorreoIgnoreCase(correo);
        return usuario.isPresent() && passwordEncoder.matches(password, usuario.get().getPassword());
    }
}
