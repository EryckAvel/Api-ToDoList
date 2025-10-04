package com.eryckavel.todolist.dto.response;

import com.eryckavel.todolist.model.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CategoriaResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime criadoEm;

    public CategoriaResponseDTO(Long id) {
        this.id = id;
    }

    public CategoriaResponseDTO(Categoria categoria) {
        this.id = categoria.getId();
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
        this.criadoEm = categoria.getCriadoEm();
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}
