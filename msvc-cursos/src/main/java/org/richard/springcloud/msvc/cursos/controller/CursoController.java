package org.richard.springcloud.msvc.cursos.controller;

import org.richard.springcloud.msvc.cursos.entity.Curso;
import org.richard.springcloud.msvc.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    @GetMapping()
    public ResponseEntity<List<Curso>> listarCurso(){
        return ResponseEntity.ok(cursoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> detalle(@PathVariable Long id){
        Optional<Curso> o=cursoService.budcarPorId(id);
        if(o.isPresent()){
           return ResponseEntity.ok(o.get());
        }
        return  ResponseEntity.notFound().build();
    }

    @PostMapping("/")
    public ResponseEntity<Curso> guardar(@RequestBody Curso curso){

        Curso cursoDb= cursoService.guardar(curso);
        return  ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Curso> editar(@RequestBody Curso curso, @PathVariable Long id){

        Optional<Curso> cursoId=cursoService.budcarPorId(id);
        if (cursoId.isPresent()){
            Curso cursoSave=new Curso();
            cursoSave.setNombre(curso.getNombre());
            return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.guardar(cursoSave));
        }
        return  ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<Curso> cursoId=cursoService.budcarPorId(id);
        if (cursoId.isPresent()){
            cursoService.eliminar(cursoId.get().getId());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
