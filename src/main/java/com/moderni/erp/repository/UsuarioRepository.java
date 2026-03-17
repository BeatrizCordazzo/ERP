package com.moderni.erp.repository;

import com.moderni.erp.model.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findFirstByCorreoIgnoreCase(String correo);
}
