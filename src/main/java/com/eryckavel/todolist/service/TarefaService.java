package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.TarefaRequestDTO;
import com.eryckavel.todolist.dto.response.TarefaResponseDTO;
import com.eryckavel.todolist.model.Categoria;
import com.eryckavel.todolist.model.Tarefa;
import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.CategoriaRepository;
import com.eryckavel.todolist.repository.TarefaRepository;
import com.eryckavel.todolist.repository.UsuarioRepository;
import com.eryckavel.todolist.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService extends BaseService<TarefaRequestDTO, TarefaResponseDTO, Tarefa>{

    private final TarefaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

    public TarefaService(JwtUtil jwtUtil, UsuarioRepository usuarioRepository, TarefaRepository repository, UsuarioRepository usuarioRepository1, CategoriaRepository categoriaRepository) {
        super(jwtUtil, usuarioRepository);
        this.repository = repository;
        this.usuarioRepository = usuarioRepository1;
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
        entidade.setTitulo(dto.titulo());
        entidade.setDescricao(dto.descricao());
        entidade.setStatus(dto.status());
        entidade.setPrioridade(dto.prioridade());
        entidade.setDataLimite(dto.dataLimite());
        if (dto.idUsuario() != null){
            Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
            entidade.setUsuario(usuario);
        }
        if (dto.idCategoria() != null){
            Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
            entidade.setCategoria(categoria);
        }
    }

    @Override
    public void converterEntidadeUpdate(Tarefa entidade, TarefaRequestDTO dto) {
        if (dto.titulo() != null) entidade.setTitulo(dto.titulo());
        if (dto.descricao() != null) entidade.setDescricao(dto.descricao());
        if (dto.status() != null) entidade.setStatus(dto.status());
        if (dto.prioridade() != null) entidade.setPrioridade(dto.prioridade());
        if (dto.dataLimite() != null) entidade.setDataLimite(dto.dataLimite());
        if (dto.idUsuario() != null){
            Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
            entidade.setUsuario(usuario);
        }
        if (dto.idCategoria() != null){
            Categoria categoria = categoriaRepository.findById(dto.idCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada!"));
            entidade.setCategoria(categoria);
        }
    }

}
