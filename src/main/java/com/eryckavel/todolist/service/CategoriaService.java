package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.CategoriaRequestDTO;
import com.eryckavel.todolist.dto.response.CategoriaResponseDTO;
import com.eryckavel.todolist.model.Categoria;
import com.eryckavel.todolist.repository.CategoriaRepository;
import com.eryckavel.todolist.repository.UsuarioRepository;
import com.eryckavel.todolist.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService extends BaseService<CategoriaRequestDTO, CategoriaResponseDTO, Categoria>{

    private final CategoriaRepository repository;

    public CategoriaService(JwtUtil jwtUtil, UsuarioRepository usuarioRepository, CategoriaRepository repository) {
        super(jwtUtil, usuarioRepository);
        this.repository = repository;
    }

    @Override
    public List<CategoriaResponseDTO> listar() {
        List<Categoria> entidades = repository.findAll();
        return entidades.stream().map(CategoriaResponseDTO::new).toList();
    }

    public CategoriaResponseDTO buscarPorId(Long idCategoria){
        Categoria categoria = repository.findById(idCategoria)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        return new CategoriaResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO salvar(CategoriaRequestDTO dto) {
        Categoria entidade = new Categoria();
        converterEntidade(entidade, dto);
        entidade = repository.save(entidade);
        return new CategoriaResponseDTO(entidade);
    }

    @Override
    public CategoriaResponseDTO atualizar(CategoriaRequestDTO dto, Long id) {
        Categoria entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        converterEntidadeUpdate(entidade, dto);
        entidade = repository.save(entidade);
        return new CategoriaResponseDTO(entidade);
    }

    @Override
    public void deletar(Long id) {
        Categoria entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
        repository.delete(entidade);
    }

    @Override
    public void converterEntidade(Categoria entidade, CategoriaRequestDTO dto) {
        entidade.setNome(dto.nome());
        entidade.setDescricao(dto.descricao());
    }

    @Override
    public void converterEntidadeUpdate(Categoria entidade, CategoriaRequestDTO dto) {
        if (dto.nome() != null) entidade.setNome(dto.nome());
        if (dto.descricao() != null) entidade.setDescricao(dto.descricao());
    }

}
