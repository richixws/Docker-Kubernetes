package org.richard.springcloud.msvc.usuarios.service;

import org.richard.springcloud.msvc.usuarios.model.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    public List<Usuario> findAll();
    public Optional<Usuario> findById(Long id);
    public Usuario saveUsuario(Usuario usuario);
    public Usuario updateUsuario(Usuario usuario, Long id);
    public void deleteUsuario(Long id);

    public Optional<Usuario> BuscarPorEmail(String email);
    //Optional<Usuario> buscarPorEmail(String email);

}
