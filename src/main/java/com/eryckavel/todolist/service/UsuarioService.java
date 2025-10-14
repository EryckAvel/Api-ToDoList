package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.UsuarioRequestDTO;
import com.eryckavel.todolist.dto.response.UsuarioResponseDTO;
import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.UsuarioRepository;
import com.eryckavel.todolist.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService extends BaseService<UsuarioRequestDTO, UsuarioResponseDTO, Usuario>{

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(JwtUtil jwtUtil, UsuarioRepository usuarioRepository, UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        super(jwtUtil, usuarioRepository);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UsuarioResponseDTO> listar() {
        List<Usuario> entidades = repository.findAll();
        return entidades.stream().map(UsuarioResponseDTO::new).toList();
    }

    @Override
    public UsuarioResponseDTO salvar(UsuarioRequestDTO dto) {
        Usuario entidade = new Usuario();
        converterEntidade(entidade, dto);
        entidade = repository.save(entidade);
        return new UsuarioResponseDTO(entidade);
    }

    @Override
    public UsuarioResponseDTO atualizar(UsuarioRequestDTO dto, Long id) {
        Usuario entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
        converterEntidadeUpdate(entidade, dto);
        entidade = repository.save(entidade);
        return new UsuarioResponseDTO(entidade);
    }

    @Override
    public void deletar(Long id) {
        Usuario entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
        repository.delete(entidade);
    }

    @Override
    public void converterEntidade(Usuario entidade, UsuarioRequestDTO dto) {
        entidade.setNome(dto.nome());
        entidade.setEmail(dto.email());
        entidade.setLogin(dto.login());
        entidade.setSenha(passwordEncoder.encode(dto.senha()));
    }

    @Override
    public void converterEntidadeUpdate(Usuario entidade, UsuarioRequestDTO dto) {
        if (dto.nome() != null) entidade.setNome(dto.nome());
        if (dto.email() != null) entidade.setEmail(dto.email());
        if (dto.login() != null) entidade.setLogin(dto.login());
        if (dto.senha() != null) entidade.setSenha(dto.senha());
    }


}
