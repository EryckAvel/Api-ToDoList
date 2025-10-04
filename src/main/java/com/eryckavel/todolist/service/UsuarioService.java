package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.UsuarioRequestDTO;
import com.eryckavel.todolist.dto.response.UsuarioResponseDTO;
import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements BaseService<UsuarioRequestDTO, UsuarioResponseDTO, Usuario>{

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
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
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        entidade.setLogin(dto.getLogin());
        entidade.setSenha(passwordEncoder.encode(dto.getSenha()));
    }

    @Override
    public void converterEntidadeUpdate(Usuario entidade, UsuarioRequestDTO dto) {
        if (dto.getNome() != null) entidade.setNome(dto.getNome());
        if (dto.getEmail() != null) entidade.setEmail(dto.getEmail());
        if (dto.getLogin() != null) entidade.setLogin(dto.getLogin());
        if (dto.getSenha() != null) entidade.setSenha(dto.getSenha());
    }


}
