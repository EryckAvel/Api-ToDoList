package com.eryckavel.todolist.repository;

import com.eryckavel.todolist.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {
}
