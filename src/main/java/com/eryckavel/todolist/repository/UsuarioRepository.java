package com.eryckavel.todolist.repository;

import com.eryckavel.todolist.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
