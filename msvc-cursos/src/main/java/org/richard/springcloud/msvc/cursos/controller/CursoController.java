package org.richard.springcloud.msvc.cursos.controller;

import feign.FeignException;
import org.richard.springcloud.msvc.cursos.models.Usuario;
import org.richard.springcloud.msvc.cursos.models.entity.Curso;
import org.richard.springcloud.msvc.cursos.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.*;

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
    public ResponseEntity<?> guardar(@Valid  @RequestBody Curso curso, BindingResult result){

        if(result.hasErrors()){
            return validar(result);
        }

        Curso cursoDb= cursoService.guardar(curso);
        return  ResponseEntity.status(HttpStatus.CREATED).body(cursoDb);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@Valid @RequestBody Curso curso,BindingResult result, @PathVariable Long id){

        if(result.hasErrors()){
            return validar(result);
        }

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

    @PutMapping("/asignar-usuario/{cursoId}")
    public ResponseEntity<?> asignarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){

         Optional<Usuario> o;
         try{
           o = cursoService.asignarUsuario(usuario,cursoId);
         }catch (FeignException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body(Collections.singletonMap("mensaje","no eciste el usuario por "+
                                        "el id o error de comunicacion:"+e.getMessage()));
         }
          if(o.isPresent()){
              return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
          }
        return ResponseEntity.notFound().build();

    }
    @PostMapping("/crear-usuario/{cursoId}")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){

        Optional<Usuario> o;
        try{
            o = cursoService.crearUsuario(usuario,cursoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje","no se pudo crear el usuario"+
                            "o error de comunicacion:"+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/eliminar-usuario/{cursoId}")
    public ResponseEntity<?> eliminarUsuario(@RequestBody Usuario usuario, @PathVariable Long cursoId){

        Optional<Usuario> o;
        try{
            o = cursoService.eliminarUsuario(usuario,cursoId);
        }catch (FeignException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("mensaje","no existe el usuario por"+
                            "el id o error en la comunicacion:"+e.getMessage()));
        }
        if(o.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }
        return ResponseEntity.notFound().build();

    }




    private static ResponseEntity<Map<String, String>> validar(BindingResult bindingResult) {
        Map<String,String> errores=new HashMap<>();
        bindingResult.getFieldErrors().forEach(err -> {
            errores.put(err.getField(),"ElCampo "+err.getField()+ "" +err.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }


}
