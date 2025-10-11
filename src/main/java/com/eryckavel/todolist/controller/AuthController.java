package com.eryckavel.todolist.controller;

import com.eryckavel.todolist.dto.request.custom.CadastroRequestDTO;
import com.eryckavel.todolist.dto.request.custom.LoginRequestDTO;
import com.eryckavel.todolist.dto.response.custom.AuthResponseDTO;
import com.eryckavel.todolist.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastroRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> autenticar(@RequestBody @Valid LoginRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.autenticar(dto));
    }

}