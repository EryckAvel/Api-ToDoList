package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.custom.CadastroRequestDTO;
import com.eryckavel.todolist.dto.request.custom.LoginRequestDTO;
import com.eryckavel.todolist.dto.response.custom.AuthResponseDTO;
import com.eryckavel.todolist.exception.exceptions.LoginException;
import com.eryckavel.todolist.model.Usuario;
import com.eryckavel.todolist.repository.UsuarioRepository;
import com.eryckavel.todolist.util.JwtUtil;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository repository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.buscarPorLogin(username)
                .orElseThrow(() -> new EntityNotFoundException("Login não encontrado!"));
    }

    public String cadastrar(CadastroRequestDTO dto){
        Optional<Usuario> existUsuarioLogin = repository.buscarPorLogin(dto.login());
        if (existUsuarioLogin.isPresent()) throw new EntityExistsException("Login de usuario ja cadastrado!");
        Usuario entidade = new Usuario();
        criarUsuario(entidade, dto);
        repository.save(entidade);
        return "Cadastro finalizado com sucesso!";
    }

    public AuthResponseDTO autenticar(LoginRequestDTO dto) {
        Usuario usuario = repository.buscarPorLogin(dto.login())
                .orElseThrow(() -> new LoginException("Login incorreto ou não cadastrado!"));
        if (!passwordEncoder.matches(dto.senha(), usuario.getPassword())) {
            throw new LoginException("Senha esta incorreta!");
        }
        String token = jwtUtil.gerarToken(usuario);
        return new AuthResponseDTO(usuario.getNome(), token);
    }

    private void criarUsuario(Usuario entidade, CadastroRequestDTO dto) {
        entidade.setNome(dto.nome());
        entidade.setEmail(dto.email());
        entidade.setLogin(dto.login());
        entidade.setSenha(passwordEncoder.encode(dto.senha()));
    }

}
