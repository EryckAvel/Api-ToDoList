package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private String login;
    private String senha;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm;

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.criadoEm = usuario.getCriadoEm();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
