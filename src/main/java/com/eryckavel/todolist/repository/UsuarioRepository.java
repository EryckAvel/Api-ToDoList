package com.eryckavel.todolist.repository;

import com.eryckavel.todolist.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM usuario WHERE login=:login", nativeQuery = true)
    Optional<Usuario> buscarPorLogin(String login);

}
