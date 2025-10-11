package com.eryckavel.todolist.util;

import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.UsuarioRepository;
import org.springframework.stereotype.Component;

@Component
public class ServiceUtil {

    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;

    public ServiceUtil(JwtUtil jwtUtil, UsuarioRepository usuarioRepository) {
        this.jwtUtil = jwtUtil;
        this.usuarioRepository = usuarioRepository;
    }

    protected Usuario buscarUsuarioLogin(String token){
        return usuarioRepository.buscarPorLogin(jwtUtil.getUsernameFromToken(token)).get();
    }

}
