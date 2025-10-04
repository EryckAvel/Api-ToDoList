package com.eryckavel.todolist.repository;

import com.eryckavel.todolist.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
