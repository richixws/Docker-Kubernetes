package org.richard.springcloud.msvc.usuarios.controller;

import org.richard.springcloud.msvc.usuarios.model.entity.Usuario;
import org.richard.springcloud.msvc.usuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public List<Usuario> listarUsuarios(){
        return usuarioService.findAll();
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Usuario> burcarPorId(@PathVariable Long id){
          Optional<Usuario> usuario=usuarioService.findById(id);
          if (!usuario.isPresent()){
              return  ResponseEntity.notFound().build();
          }
       return new  ResponseEntity<>(usuario.get(),HttpStatus.OK);
    }

    @PostMapping("/usuario")
    public ResponseEntity<Usuario> saveUsuario(@Valid @RequestBody Usuario usuario, BindingResult  bindingResult ){

        if(bindingResult.hasErrors()){
            Map<String,String> errores=new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> {
                errores.put(err.getField(),"ElCampo "+err.getField()+ "" +err.getDefaultMessage());
            });
            return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
        }

        Usuario usuariosave=usuarioService.saveUsuario(usuario);
        return new ResponseEntity<>(usuariosave,HttpStatus.CREATED);
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario,@PathVariable Long id){

        Usuario usuarioupdate=usuarioService.updateUsuario(usuario,id);
        return new ResponseEntity<>(usuarioupdate, HttpStatus.OK);
    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){

        Optional<Usuario> usuarioId=usuarioService.findById(id);
        if(!usuarioId.isPresent()){
            return  ResponseEntity.notFound().build();
        }
        usuarioService.deleteUsuario(id);
        return ResponseEntity.notFound()    .build();
    }

}
