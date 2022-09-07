package com.example.IvarBots.repositorio;

import com.example.IvarBots.dominio.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    List<Usuario> findByNomeUsuarioAndSenha(String nomeUsuario, String Senha);
}
