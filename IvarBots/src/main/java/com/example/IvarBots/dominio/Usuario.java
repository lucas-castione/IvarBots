package com.example.IvarBots.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private String nomeUsuario;

    private String senha;

    private String nomeConta;


    //getter

    public Integer getId() {
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getNomeConta() {
        return nomeConta;
    }


    //setters

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }
}
