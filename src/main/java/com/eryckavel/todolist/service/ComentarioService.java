package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.ComentarioRequestDTO;
import com.eryckavel.todolist.dto.response.ComentarioResponseDTO;
import com.eryckavel.todolist.model.Comentario;
import com.eryckavel.todolist.model.Tarefa;
import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.ComentarioRepository;
import com.eryckavel.todolist.repository.TarefaRepository;
import com.eryckavel.todolist.repository.UsuarioRepository;
import com.eryckavel.todolist.util.JwtUtil;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioService extends BaseService<ComentarioRequestDTO, ComentarioResponseDTO, Comentario> {

    private final ComentarioRepository repository;
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioService(JwtUtil jwtUtil, UsuarioRepository usuarioRepository, ComentarioRepository repository, TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository1) {
        super(jwtUtil, usuarioRepository);
        this.repository = repository;
        this.tarefaRepository = tarefaRepository;
        this.usuarioRepository = usuarioRepository1;
    }

    @Override
    public List<ComentarioResponseDTO> listar() {
        List<Comentario> entidades = repository.findAll();
        return entidades.stream().map(ComentarioResponseDTO::new).toList();
    }

    @Override
    public ComentarioResponseDTO salvar(ComentarioRequestDTO dto) {
        Comentario entidade = new Comentario();
        converterEntidade(entidade, dto);
        entidade = repository.save(entidade);
        return new ComentarioResponseDTO(entidade);
    }

    @Override
    public ComentarioResponseDTO atualizar(ComentarioRequestDTO dto, Long id) {
        Comentario entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario não encontrado!"));
        converterEntidadeUpdate(entidade, dto);
        entidade = repository.save(entidade);
        return new ComentarioResponseDTO(entidade);
    }

    @Override
    public void deletar(Long id) {
        Comentario entidade = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comentario não encontrado!"));
        repository.delete(entidade);
    }

    @Override
    public void converterEntidade(Comentario entidade, ComentarioRequestDTO dto) {
        if (dto.idUsuario() != null){
            Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
            entidade.setUsuario(usuario);
        }
        if (dto.idTarefa() != null){
            Tarefa tarefa = tarefaRepository.findById(dto.idTarefa())
                    .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada!"));
            entidade.setTarefa(tarefa);
        }
        entidade.setConteudo(dto.conteudo());
    }

    @Override
    public void converterEntidadeUpdate(Comentario entidade, ComentarioRequestDTO dto) {
        if (dto.idUsuario() != null){
            Usuario usuario = usuarioRepository.findById(dto.idUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado!"));
            entidade.setUsuario(usuario);
        }
        if (dto.idTarefa() != null){
            Tarefa tarefa = tarefaRepository.findById(dto.idTarefa())
                    .orElseThrow(() -> new EntityNotFoundException("Tarefa não encontrada!"));
            entidade.setTarefa(tarefa);
        }
        if (dto.conteudo() != null) entidade.setConteudo(dto.conteudo());
    }

}
