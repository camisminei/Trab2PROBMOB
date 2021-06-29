package com.example.trabfinal;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id; //id chave primaria
    private String nome, email, telefone, senha;

    public Usuario(){}
    public Usuario(int id, String nome, String email, String telefone, String senha) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;

    }

    public Usuario (String nome, String email, String telefone, String senha) {

        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;

    }

    public Usuario (String email) {
        this.email = email;
    }

    public int getIdUsuario() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
