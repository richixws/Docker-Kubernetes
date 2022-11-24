package org.richard.springcloud.msvc.cursos.repository;

import org.richard.springcloud.msvc.cursos.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso,Long> {

}
