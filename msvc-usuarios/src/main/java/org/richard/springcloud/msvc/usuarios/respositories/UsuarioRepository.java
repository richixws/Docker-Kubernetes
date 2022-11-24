package org.richard.springcloud.msvc.usuarios.respositories;

import org.richard.springcloud.msvc.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {



}
