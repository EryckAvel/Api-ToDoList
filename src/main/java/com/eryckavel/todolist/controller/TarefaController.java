package com.eryckavel.todolist.controller;

import com.eryckavel.todolist.dto.request.TarefaRequestDTO;
import com.eryckavel.todolist.dto.response.TarefaResponseDTO;
import com.eryckavel.todolist.service.TarefaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefa")
@Tag(name = "Tarefa")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TarefaResponseDTO>> listarTarefas(){
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> salvarTarefa(@RequestBody TarefaRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaResponseDTO> atualizarTarefa(@PathVariable("id") Long id, @RequestBody TarefaRequestDTO dto){
        return ResponseEntity.ok(service.atualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable("id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
