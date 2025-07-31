package com.eryckavel.todolist.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(name = "idcategoria")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    @CreationTimestamp
    private LocalDateTime criadoEm;

    public Categoria() {
    }

    public Categoria(Long id, String descricao, LocalDateTime criadoEm) {
        this.id = id;
        this.descricao = descricao;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
