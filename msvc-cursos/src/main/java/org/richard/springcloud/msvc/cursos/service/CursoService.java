package org.richard.springcloud.msvc.cursos.service;

import org.richard.springcloud.msvc.cursos.models.Usuario;
import org.richard.springcloud.msvc.cursos.models.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();
    Optional<Curso> budcarPorId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);

    //loguica de negocio de datos de otro servicio

    Optional<Usuario> asignarUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> crearUsuario(Usuario usuario, Long cursoId);
    Optional<Usuario> eliminarUsuario(Usuario usuario, Long cursoId);



}
