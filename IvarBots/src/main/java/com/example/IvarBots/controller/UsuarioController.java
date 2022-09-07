package com.example.IvarBots.controller;

import com.example.IvarBots.dominio.Usuario;
import com.example.IvarBots.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrar(@RequestBody Usuario user){
        repository.save(user);
        return ResponseEntity.status(201).body(user);

    }

    @GetMapping("/login/{usuario}/{senha}")
    public ResponseEntity login(@PathVariable String usuario, @PathVariable String senha){
        List<Usuario> users = repository.findByNomeUsuarioAndSenha(usuario, senha);
        if(users.isEmpty()){
            return ResponseEntity.status(404).build();
        }

        return ResponseEntity.status(200).body(users.get(0));

    }

    @PutMapping
    public ResponseEntity atualizar(@RequestBody Usuario user){
        repository.save(user);
        return ResponseEntity.status(201).body(user);

    }


}
