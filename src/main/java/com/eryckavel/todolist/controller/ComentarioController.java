package com.eryckavel.todolist.controller;

import com.eryckavel.todolist.dto.request.ComentarioRequestDTO;
import com.eryckavel.todolist.dto.response.ComentarioResponseDTO;
import com.eryckavel.todolist.service.ComentarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comentario")
public class ComentarioController {

    private final ComentarioService service;

    public ComentarioController(ComentarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ComentarioResponseDTO>> listarComentarios(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listar());
    }

    @PostMapping
    public ResponseEntity<ComentarioResponseDTO> salvarComentario(@RequestBody ComentarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComentarioResponseDTO> atualizarComentario(@PathVariable("id") Long id, @RequestBody ComentarioRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(dto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable("id") Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
