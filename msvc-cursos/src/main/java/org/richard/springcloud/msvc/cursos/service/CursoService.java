package org.richard.springcloud.msvc.cursos.service;

import org.richard.springcloud.msvc.cursos.entity.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoService {

    List<Curso> listar();
    Optional<Curso> budcarPorId(Long id);
    Curso guardar(Curso curso);
    void eliminar(Long id);
}
