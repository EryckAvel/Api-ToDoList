package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.TarefaRequestDTO;
import com.eryckavel.todolist.dto.response.TarefaResponseDTO;
import com.eryckavel.todolist.model.Categoria;
import com.eryckavel.todolist.model.Tarefa;
import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.CategoriaRepository;
import com.eryckavel.todolist.repository.TarefaRepository;
import com.eryckavel.todolist.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService implements BaseService<TarefaRequestDTO, TarefaResponseDTO, Tarefa>{

    private final TarefaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public TarefaService(TarefaRepository repository,
                         UsuarioRepository usuarioRepository,
                         CategoriaRepository categoriaRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
    }


    @Override
    public List<TarefaResponseDTO> listar() {
        List<Tarefa> entidades = repository.findAll();
        return entidades.stream().map(TarefaResponseDTO::new).toList();
    }

    @Override
    public TarefaResponseDTO salvar(TarefaRequestDTO dto) {
        Tarefa entidade = new Tarefa();
        converterEntidade(entidade, dto);
        entidade = repository.save(entidade);
        return new TarefaResponseDTO(entidade);
    }

    @Override
    public TarefaResponseDTO atualizar(TarefaRequestDTO dto, Long id) {
        Tarefa entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada!"));
        converterEntidadeUpdate(entidade, dto);
        entidade = repository.save(entidade);
        return new TarefaResponseDTO(entidade);
    }

    @Override
    public void deletar(Long id) {
        Tarefa entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada!"));
        repository.delete(entidade);
    }

    @Override
    public void converterEntidade(Tarefa entidade, TarefaRequestDTO dto) {
        entidade.setTitulo(dto.getTitulo());
        entidade.setDescricao(dto.getDescricao());
        entidade.setStatus(dto.getStatus());
        entidade.setPrioridade(dto.getPrioridade());
        entidade.setDataLimite(dto.getDataLimite());
        if (dto.getIdUsuario() != null){
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
            entidade.setUsuario(usuario);
        }
        if (dto.getIdCategoria() != null){
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
            entidade.setCategoria(categoria);
        }
    }

    @Override
    public void converterEntidadeUpdate(Tarefa entidade, TarefaRequestDTO dto) {
        if (dto.getTitulo() != null) entidade.setTitulo(dto.getTitulo());
        if (dto.getDescricao() != null) entidade.setDescricao(dto.getDescricao());
        if (dto.getStatus() != null) entidade.setStatus(dto.getStatus());
        if (dto.getPrioridade() != null) entidade.setPrioridade(dto.getPrioridade());
        if (dto.getDataLimite() != null) entidade.setDataLimite(dto.getDataLimite());
        if (dto.getIdUsuario() != null){
            Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
            entidade.setUsuario(usuario);
        }
        if (dto.getIdCategoria() != null){
            Categoria categoria = categoriaRepository.findById(dto.getIdCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
            entidade.setCategoria(categoria);
        }
    }

}
