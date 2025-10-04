package com.eryckavel.todolist.service;

import java.util.List;

public interface BaseService <Request, Response, Entity> {

    List<Response> listar();
    Response salvar(Request dto);
    Response atualizar(Request dto, Long id);
    void deletar(Long id);
    void converterEntidade(Entity entidade, Request dto);
    void converterEntidadeUpdate(Entity entidade, Request dto);

}
