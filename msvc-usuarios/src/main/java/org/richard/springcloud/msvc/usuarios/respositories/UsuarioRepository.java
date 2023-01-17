package org.richard.springcloud.msvc.usuarios.respositories;

import org.richard.springcloud.msvc.usuarios.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

     Optional<Usuario> findByEmail(String email);

     @Query("select u from Usuario u where  u.email=?1")
     Optional<Usuario> buscarPorEmail(String email);

}
