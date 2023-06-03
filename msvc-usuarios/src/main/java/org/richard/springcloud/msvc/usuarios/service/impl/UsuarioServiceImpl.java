package org.richard.springcloud.msvc.usuarios.service.impl;

import org.richard.springcloud.msvc.usuarios.model.entity.Usuario;
import org.richard.springcloud.msvc.usuarios.respositories.UsuarioRepository;
import org.richard.springcloud.msvc.usuarios.service.UsuarioService;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {



    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        Optional<Usuario> usuarioId=usuarioRepository.findById(id);
        if(!usuarioId.isPresent()){
            System.out.println("id noe encontrado");
        }
        return usuarioId;
    }

    @Override
    @Transactional
    public Usuario saveUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario updateUsuario(Usuario usuario, Long id) {

        Optional<Usuario> usuarioid=usuarioRepository.findById(id);
        if (!usuarioid.isPresent()){
            System.out.println("id no encontrado");
        }

        Usuario usuariosave = usuarioid.get();
        usuariosave.setNombre(usuario.getNombre());
        usuariosave.setEmail(usuario.getEmail());
        usuariosave.setPassword(usuario.getPassword());

        Usuario usuarioupdate=usuarioRepository.save(usuariosave);

        return usuarioupdate;
    }

    @Override
    @Transactional
    public void deleteUsuario(Long id) {
         usuarioRepository.deleteById(id);
    }

    @Override
    public List<Usuario> listarPorIds(Iterable<Long> ids) {


        return usuarioRepository.findAllById(ids);
    }

    @Override
    public Optional<Usuario> BuscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

}
