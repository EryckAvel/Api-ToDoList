package com.eryckavel.todolist.service;

import com.eryckavel.todolist.dto.request.CategoriaRequestDTO;
import com.eryckavel.todolist.dto.response.CategoriaResponseDTO;
import com.eryckavel.todolist.model.Categoria;
import com.eryckavel.todolist.repository.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceTest {

    @InjectMocks
    private CategoriaService categoriaService;
    @Mock
    private CategoriaRepository categoriaRepository;
    Categoria categoria1;
    Categoria categoria2;
    Categoria categoriaAtualizada;
    CategoriaResponseDTO categoriaResponse1;
    CategoriaResponseDTO categoriaAtualizadaResponse;
    CategoriaResponseDTO categoriaResponse2;
    CategoriaRequestDTO categoriaRequest2;

    @BeforeEach
    void setUp(){
        categoria1 = new Categoria(1L, "Categoria 1", "Apenas a Categoria 1", LocalDateTime.now());
        categoriaResponse1 = new CategoriaResponseDTO(categoria1);
        categoriaRequest2 = new CategoriaRequestDTO("Categoria 2", "Apenas a Categoria 2");
        categoria2 = new Categoria(2L, "Categoria 2", "Apenas a Categoria 2", LocalDateTime.now());
        categoriaResponse2 = new CategoriaResponseDTO(categoria2);
        categoriaAtualizada = new Categoria(1L, "Categoria 2", "Apenas a Categoria 2", LocalDateTime.now());
        categoriaAtualizadaResponse = new CategoriaResponseDTO(categoriaAtualizada);
    }

    @Test
    @DisplayName("Deve listar categorias corretamente!")
    void deveListarTodasAsCategorias(){
        when(categoriaRepository.findAll()).thenReturn(Collections.singletonList(categoria1));
        List<CategoriaResponseDTO> categorias = categoriaService.listar();
        assertEquals(Collections.singletonList(categoriaResponse1).stream().toList(), categorias, "Quantidade de categorias listadas incorreta!");
        verify(categoriaRepository).findAll();
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve buscar uma categoria por id corretamente!")
    void deveBuscarComSucessoUmaCategoriaPorId(){
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria1));
        CategoriaResponseDTO categoriaBusca = categoriaService.buscarPorId(1L);
        assertNotNull(categoriaBusca, "Objeto null!");
        assertEquals(categoriaResponse1, categoriaBusca, "As categorias não são iguais!");
        verify(categoriaRepository).findById(1L);
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve retornar uma exception caso não encontre a categoria por Id!")
    void deveRetornarUmaExceptionAoBuscarUmIdInvalido(){
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoriaService.buscarPorId(99L));
        verify(categoriaRepository).findById(99L);
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve salvar com sucesso uma nova categoria!")
    void deveSalvarCorretamenteUmaCategoria(){
        when(categoriaRepository.save(any())).thenReturn(categoria2);
        CategoriaResponseDTO categoriaSalva = categoriaService.salvar(categoriaRequest2);
        assertNotNull(categoriaSalva);
        assertEquals(categoriaResponse2, categoriaSalva, "As entidades de categoria não batem!");
        verify(categoriaRepository).save(any());
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve Atualizar com sucesso uma nova categoria!")
    void deveAtualizarCorretamenteUmaCategoria(){
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria1));
        when(categoriaRepository.save(any())).thenReturn(categoriaAtualizada);
        CategoriaResponseDTO categoriaAtualizada = categoriaService.atualizar(categoriaRequest2, 1L);
        assertNotNull(categoriaAtualizada);
        assertEquals(categoriaAtualizadaResponse, categoriaAtualizada,"As entidades de categoria não batem!");
        verify(categoriaRepository).findById(1L);
        verify(categoriaRepository).save(any());
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve retornar uma exception caso não encontre a categoria por Id para atualizar!")
    void deveRetornarUmaExceptionAoBuscarUmIdInvalidoParaAtualizarUmaCategoria(){
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> categoriaService.atualizar(categoriaRequest2,99L));
        verify(categoriaRepository).findById(99L);
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve Deletar com sucesso uma categoria passando o Id!")
    void deveRetornarSucessoAoDeletarUmaCategoria(){
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria2));
        doNothing().when(categoriaRepository).delete(categoria2);
        categoriaService.deletar(1L);
        verify(categoriaRepository).findById(1L);
        verify(categoriaRepository).delete(categoria2);
        verifyNoMoreInteractions(categoriaRepository);
    }

    @Test
    @DisplayName("Deve retornar uma exception caso não encontre a categoria por Id para deletar!")
    void  deveRetornarUmaExceptionAoBuscarUmIdInvalidoParaDeletarUmaCategoria() {
        when(categoriaRepository.findById(99L)).thenReturn(Optional.empty());
        assertThrows (EntityNotFoundException.class, () -> categoriaService.deletar(99L));
        verify(categoriaRepository).findById(99L);
        verifyNoMoreInteractions(categoriaRepository);
    }

}
