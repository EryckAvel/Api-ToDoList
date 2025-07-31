package com.eryckavel.todolist.model;

import com.eryckavel.todolist.enums.PrioridadeTarefa;
import com.eryckavel.todolist.enums.StatusTarefa;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tarefa")
public class Tarefa {

    @Id
    @Column(name = "idtarefa")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private StatusTarefa status;
    private PrioridadeTarefa prioridade;
    private LocalDate dataLimite;
    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "idcategoria")
    private Categoria categoria;
    @CreationTimestamp
    private LocalDateTime criadoEm;
    @UpdateTimestamp
    private LocalDateTime atualizadoEm;

    public Tarefa() {
    }

    public Tarefa(Long id, String titulo, String descricao, StatusTarefa status, PrioridadeTarefa prioridade, LocalDate dataLimite, Usuario usuario, Categoria categoria, LocalDateTime criadoEm, LocalDateTime atualizadoEm) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
        this.dataLimite = dataLimite;
        this.usuario = usuario;
        this.categoria = categoria;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }

    public PrioridadeTarefa getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(PrioridadeTarefa prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public LocalDateTime getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(LocalDateTime atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
