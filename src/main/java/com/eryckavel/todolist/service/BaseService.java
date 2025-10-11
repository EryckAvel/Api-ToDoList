package com.eryckavel.todolist.service;

import com.eryckavel.todolist.repository.UsuarioRepository;
import com.eryckavel.todolist.util.JwtUtil;
import com.eryckavel.todolist.util.ServiceUtil;

import java.util.List;

public abstract class BaseService <Request, Response, Entity> extends ServiceUtil {

    public BaseService(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        super(jwtUtil, usuarioRepository);
    }

    abstract List<Response> listar();
    abstract Response salvar(Request dto);
    abstract Response atualizar(Request dto, Long id);
    abstract void deletar(Long id);
    abstract void converterEntidade(Entity entidade, Request dto);
    abstract void converterEntidadeUpdate(Entity entidade, Request dto);

}
