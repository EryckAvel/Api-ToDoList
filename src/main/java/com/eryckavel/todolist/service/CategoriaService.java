package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.CategoriaRequestDTO;
import com.eryckavel.todolist.dto.response.CategoriaResponseDTO;
import com.eryckavel.todolist.model.Categoria;
import com.eryckavel.todolist.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements BaseService<CategoriaRequestDTO, CategoriaResponseDTO, Categoria>{

    private final CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoriaResponseDTO> listar() {
        List<Categoria> entidades = repository.findAll();
        return entidades.stream().map(CategoriaResponseDTO::new).toList();
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
        entidade.setNome(dto.getNome());
        entidade.setDescricao(dto.getDescricao());
    }

    @Override
    public void converterEntidadeUpdate(Categoria entidade, CategoriaRequestDTO dto) {
        if (dto.getNome() != null) entidade.setNome(dto.getNome());
        if (dto.getDescricao() != null) entidade.setDescricao(dto.getDescricao());
    }

}
